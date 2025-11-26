package jp.co.basenet.weolab.tms_service.personel.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.basenet.weolab.tms_service.common.service.ChatGptService;
import jp.co.basenet.weolab.tms_service.personel.dto.ResumeAnalysis;
import jp.co.basenet.weolab.tms_service.personel.dto.ResumeRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnalyseResumeService {
    private final ObjectMapper om;
    private final ChatGptService gptService;

    public ResumeAnalysis analyseResume(ResumeRequest request) {
        try {
            String inputJson = om.writeValueAsString(request);
            Map<String, String> model = new HashMap<String, String>();
            model.put("経歴データ", inputJson);
            String prompt = gptService.getPrompt("analyse-work-experience", model);
            System.out.println(prompt);
            String gptResp = gptService.askChatGpt(prompt);
            System.out.println(gptResp);
            return om.readValue(gptResp, new TypeReference<ResumeAnalysis>(){});

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
