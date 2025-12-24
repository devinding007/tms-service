package jp.co.basenet.weolab.tms_service.exam.controller;

import jp.co.basenet.weolab.tms_service.exam.dto.ExamRunDTO;
import jp.co.basenet.weolab.tms_service.exam.service.ExamRunService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-sessions")
public class ExamSessionController {

    private final ExamRunService examRunService;

    public ExamSessionController(ExamRunService examRunService) {
        this.examRunService = examRunService;
    }

    @GetMapping("/{examLinkId}")
    public ExamRunDTO getExamSession(@PathVariable String examLinkId) {
        ExamRunDTO dto = examRunService.getExamRun(examLinkId);
        // 清空模範回答（由前端或後端處理）
        // 此處假設 ExamPaperResponse 已在 service 層脫敏
        return dto;
    }
}