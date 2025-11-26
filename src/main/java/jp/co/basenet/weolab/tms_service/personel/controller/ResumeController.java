package jp.co.basenet.weolab.tms_service.personel.controller;

import jakarta.validation.Valid;
import jp.co.basenet.weolab.tms_service.personel.dto.ResumeAnalysis;
import jp.co.basenet.weolab.tms_service.personel.dto.ResumeRequest;
import jp.co.basenet.weolab.tms_service.personel.service.AnalyseResumeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResumeController {

    private final AnalyseResumeService analyseResumeService;

    @PostMapping(value = "/resume/analyse", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResumeAnalysis> receiveResume(@Valid @RequestBody ResumeRequest request) {
        StopWatch sw = new StopWatch("analyse-work-exp");
        sw.start("call-chatgpt");
        ResumeAnalysis gptRes = analyseResumeService.analyseResume(request);
        sw.stop();
        System.out.println(sw.prettyPrint());
        return ResponseEntity.ok(gptRes);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
