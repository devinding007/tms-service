package jp.co.basenet.weolab.tms_service.exam.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.basenet.weolab.tms_service.exam.dto.GenerationRequest;
import jp.co.basenet.weolab.tms_service.exam.dto.Question;
import jp.co.basenet.weolab.tms_service.exam.service.ProblemGenerateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ObjectMapper om;
    private final ProblemGenerateService generateService;

    @PostMapping(value = "/generate", consumes = "application/json")
    public ResponseEntity<List<Question>> generate(@RequestBody GenerationRequest req) throws JsonProcessingException {
        System.out.println(om.writeValueAsString(req));
        List<Question> result = List.of();
        try {
            result = generateService.generateQuestionList(req);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");

        }
        System.out.println("Okay!");
        return ResponseEntity.ok(result);
    }

}
