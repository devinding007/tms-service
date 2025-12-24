package jp.co.basenet.weolab.tms_service.exam.controller;

import jp.co.basenet.weolab.tms_service.exam.dto.ExamPaperDetailResponse;
import jp.co.basenet.weolab.tms_service.exam.dto.ExamRunDTO;
import jp.co.basenet.weolab.tms_service.exam.dto.ExamSubmissionRequest;
import jp.co.basenet.weolab.tms_service.exam.dto.SkillReflectionResult;
import jp.co.basenet.weolab.tms_service.exam.entity.ExamPaperDoc;
import jp.co.basenet.weolab.tms_service.exam.entity.ExamRunDoc;
import jp.co.basenet.weolab.tms_service.exam.service.ExamRunService;
import jp.co.basenet.weolab.tms_service.common.dto.ApiListResult;
import jp.co.basenet.weolab.tms_service.exam.repository.ExamRunRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import jp.co.basenet.weolab.tms_service.exam.repository.ExamPaperRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exam-runs")
public class ExamRunController {

    private final ExamRunService examRunService;
    private final ExamRunRepository examRunRepository; //
    private final ExamPaperRepository examPaperRepository;


    public ExamRunController(ExamRunService examRunService, ExamRunRepository examRunRepository, ExamPaperRepository examPaperRepository) {
        this.examRunService = examRunService;
        this.examRunRepository = examRunRepository;
        this.examPaperRepository = examPaperRepository;
    }

    @GetMapping
    public ApiListResult<ExamRunDTO> listExamRuns(
            @RequestParam(required = false) String idLike,
            @RequestParam(required = false) String userLike,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ExamRunDoc> pageResult;

        if (status != null) {
            pageResult = examRunRepository.findByStatus(status, pageable);
        } else if (userLike != null && !userLike.isEmpty()) {
            pageResult = examRunRepository.findByParticipantNameContaining(userLike, pageable);
        } else if (idLike != null && !idLike.isEmpty()) {
            pageResult = examRunRepository.findByExamRunIdContaining(idLike, pageable);
        } else {
            pageResult = examRunRepository.findAll(pageable);
        }

        //
        List<ExamRunDTO> dtoList = pageResult.getContent().stream()
                .map(entity -> {
                    ExamRunDTO dto = new ExamRunDTO();
                    dto.set試験ＩＤ(entity.getExamRunId());
                    dto.set参加者人材ＩＤ(entity.getTalentId());
                    dto.set参加者氏名(entity.getParticipantName());
                    dto.set登録済人材(entity.getIsRegisteredTalent() ? 1 : 0);
                    dto.set試験ステータス(entity.getStatus());
                    dto.set試験用紙ＩＤ(entity.getPaperId());
                    dto.set試験提出日時(entity.getSubmittedAt());
                    dto.set試験正解数(entity.getCorrectCount());
                    dto.set試験問題数(entity.getTotalQuestions());
                    dto.set試験正解数(entity.getCorrectCount());

                    if (entity.getPaperId() != null) {
                        ExamPaperDetailResponse paper = new ExamPaperDetailResponse();
                        paper.set試験用紙ＩＤ(entity.getPaperId());
                        // 查詢名稱
                        Optional<ExamPaperDoc> paperOpt = examPaperRepository.findById(entity.getPaperId());
                        paper.set試験用紙名称(paperOpt.map(ExamPaperDoc::getPaperName).orElse("（削除済）"));
                        dto.set試験用紙(paper); //
                    }
                    // 注意：這裡未加載 試験用紙 詳細（與前端行為一致）
                    return dto;
                })
                .toList();


        ApiListResult<ExamRunDTO> result = new ApiListResult<>();
        result.setItems(dtoList);
        result.setTotal(pageResult.getTotalElements());
        return result;
    }

    @GetMapping("/{id}")
    public ExamRunDTO getExamRun(@PathVariable String id) {
        return examRunService.getExamRun(id);
    }

    @PostMapping
    public ExamRunDTO createExamRun(@RequestBody ExamRunDTO dto) {
        return examRunService.createExamRun(dto);
    }

    @PatchMapping("/{id}/confirm")
    public ExamRunDTO confirmExamRun(@PathVariable String id) {
        return examRunService.confirmExamRun(id);
    }

    @PostMapping("/{id}/start")
    public ExamRunDTO startExamRun(@PathVariable String id) {
        return examRunService.startExamRun(id);
    }

    @PostMapping("/submit")
    public void submitExamAnswers(@RequestBody ExamSubmissionRequest request) {
        examRunService.submitExamAnswers(request);
    }

    @PostMapping("/{id}/reflect")
    public List<SkillReflectionResult> reflectSkillPoint(@PathVariable String id) {
        return examRunService.reflectSkillPoint(id);
    }
}