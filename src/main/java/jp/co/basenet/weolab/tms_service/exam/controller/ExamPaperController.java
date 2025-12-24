// src/main/java/.../exam/controller/ExamPaperController.java
package jp.co.basenet.weolab.tms_service.exam.controller;

import jp.co.basenet.weolab.tms_service.common.dto.ApiListResult;
import jp.co.basenet.weolab.tms_service.exam.dto.ExamPaperDetail;
import jp.co.basenet.weolab.tms_service.exam.dto.ExamPaperRequest;
import jp.co.basenet.weolab.tms_service.exam.dto.ExamPaperResponse;
import jp.co.basenet.weolab.tms_service.exam.entity.ExamPaperDoc;
import jp.co.basenet.weolab.tms_service.exam.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exam-papers")
public class ExamPaperController {

    @Autowired
    private ExamPaperService examPaperService;

    @GetMapping
    public ResponseEntity<ApiListResult<ExamPaperResponse>> listPapers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ExamPaperDoc> entities;

        if (keyword != null && !keyword.trim().isEmpty()) {
            // ★★ 修正：examPaperService 経由で呼び出し ★★
            entities = examPaperService.findByKeyword(keyword.trim(), pageable);
        } else {
            // ★★ 修正：examPaperService 経由で呼び出し ★★
            entities = examPaperService.findByFilters(name, description, pageable);
        }

        List<ExamPaperResponse> dtos = entities.getContent().stream()
                .map(entity -> {
                    ExamPaperResponse dto = new ExamPaperResponse();
                    dto.setPaperId(entity.getPaperId());
                    dto.setPaperName(entity.getPaperName());
                    dto.setDescription(entity.getDescription());
                    dto.setCreatedAt(entity.getCreatedAt());
                    dto.setDeletedAt(entity.getDeletedAt());
                    return dto;
                })
                .collect(Collectors.toList());

        // ★★ フロントエンドが期待する形式でラップ ★★
        ApiListResult<ExamPaperResponse> result = new ApiListResult<>();
        result.setItems(dtos);
        result.setTotal(entities.getTotalElements());

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> savePaper(@RequestBody ExamPaperRequest request) {
        // ★★ 修正：ネストされた paper オブジェクトから取得 ★★
        String paperName = request.getPaper() != null ? request.getPaper().getPaperName() : null;

        // ★★ null/empty チェックを追加（安全対策）★★
        if (request.getPaper() == null ||
                paperName == null ||
                paperName.trim().isEmpty()) {
            System.err.println("=== エラー: 試験用紙名称が空です ===");
            throw new IllegalArgumentException("試験用紙名称は必須です");
        }

        try {
            String paperId = examPaperService.savePaper(request);
            Map<String, String> response = new HashMap<>();
            response.put("paper_id", paperId);

            System.out.println("=== 保存成功: " + paperId + " ===");
            return ResponseEntity.ok(paperId);
        } catch (Exception e) {
            System.err.println("=== savePaper エラー発生 ===");
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamPaperDetail> getPaper(@PathVariable String id) {
        ExamPaperDetail detail = examPaperService.getPaper(id);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaper(@PathVariable String id) {
        examPaperService.deletePaper(id);
        return ResponseEntity.noContent().build();
    }
}