package jp.co.basenet.weolab.tms_service.personel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.basenet.weolab.tms_service.common.service.ChatGptService;
import jp.co.basenet.weolab.tms_service.personel.dto.ProposalAnalyseRequest;
import jp.co.basenet.weolab.tms_service.personel.dto.ProposalAnalyseResult;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnalyseProposalService {
    private final ObjectMapper om;
    private final ChatGptService gptService;

    public List<ProposalAnalyseResult> analyse(ProposalAnalyseRequest request) {
        try {
            String inputJson = om.writeValueAsString(request);

            Map<String, String> model = new HashMap<String, String>();
            model.put("候補要員データ", inputJson);
            String prompt = gptService.getPrompt("candidate-matching", model);

            System.out.println(prompt);
            String gptResp = gptService.askChatGpt(prompt);
            System.out.println(gptResp);
            return om.readValue(gptResp, new TypeReference<List<ProposalAnalyseResult>>(){});

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
}
