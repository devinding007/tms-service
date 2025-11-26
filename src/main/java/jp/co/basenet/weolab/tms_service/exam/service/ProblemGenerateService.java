package jp.co.basenet.weolab.tms_service.exam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.basenet.weolab.tms_service.common.service.ChatGptService;
import jp.co.basenet.weolab.tms_service.exam.dto.GenerationRequest;
import jp.co.basenet.weolab.tms_service.exam.dto.Question;

@Component
public class ProblemGenerateService {
    private ObjectMapper om;
    private ChatGptService gptService;

    public ProblemGenerateService(ChatGptService gptService, ObjectMapper om) {
        this.gptService = gptService;
        this.om = om;
    }

    public List<Question> generateQuestionList(GenerationRequest request) {
        Map<String, String> model = new HashMap<>();
        model.put("levelFrom", request.getLevelFrom());
        model.put("levelTo", request.getLevelTo());
        model.put("skills", request.getSkills());
        model.put("jobPosting", request.getJobPosting());
        model.put("number", request.getNumber());
        String prompt = gptService.getPrompt("generate-exam-question", model);
        StopWatch sw = new StopWatch("exam");
        sw.start("call-chatgpt");
        System.out.println(prompt);
        String result = gptService.askChatGpt(prompt);
        List<Question> resultList = null;
        try {
            resultList = om.readValue(result, new TypeReference<List<Question>>() {
            });
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            System.out.println(result);
            // …処理…
            sw.stop();
            System.out.println(sw.prettyPrint()); // タスク別に表示
        }
        return resultList;

    }
}
