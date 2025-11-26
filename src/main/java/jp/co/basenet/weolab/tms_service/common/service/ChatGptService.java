package jp.co.basenet.weolab.tms_service.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

import jp.co.basenet.weolab.tms_service.common.repository.PromptTemplateRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    public static void main(String[] args) {
        // ChatGptService service = new
        // ChatGptService("c2stcHJvai1oWUVTNzVlV2tXci1yU1QzM3N5SFNlZmNZejRkSTBrNEZDay10eWJMbDJNODVaUjQzTnBHXzJiX2NMWXZtd2tFZXNlNHBZbi14dlQzQmxia0ZKVVZNeGNGRkx0SC1wU2RVelFkWGZCcWdVcVlKOFd0MWF5Y2w0MG9VTTFEN25WWE5aM0Y3b3FNLUFsdkFzMGdrWXdmZ0FQSzV1c0E=");
        // String res = service.askChatGpt("こんにちわ！IT技術問題３問を生成してくれる？");
        // System.out.println(res);
    }

    private final WebClient webClient;
    private PromptTemplateRepository promptRepo;

    // public ChatGptService(@Value("${openai.api-key}") String apiKey) {
    // this.webClient = WebClient.builder()
    // .baseUrl("https://api.openai.com/v1")
    // .defaultHeader("Authorization", "Bearer " + apiKey)
    // .defaultHeader("Content-Type", "application/json")
    // .build();
    // }

    public ChatGptService(@Value("${openai.api-key}") String apiKey, PromptTemplateRepository promptRepo) {
        this.promptRepo = promptRepo;
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public String getPrompt(String templateName, Map<String, String> model) {
        return promptRepo.render(templateName, model);
    }

    public String askChatGpt(String prompt) {
        Map<String, Object> request = Map.of(
            "model", "gpt-5-nano",
            // "model", "gpt-4o-mini-2024-07-18",
            // "service_tier", "priority",
            // "verbosity", "low",
            // "reasoning", List.of(Map.of("effort", "minimal")),
            "messages",List.of(
                // Map.of("role","system","content","You are a helpful assistant."),
                Map.of("role","user","content",prompt)
                ));

    Map<String, Object> response = webClient.post()
            .uri("/chat/completions")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(Map.class)
            .block();

    // レスポンスの中から回答を取り出す
    var choices = (java.util.List<Map<String, Object>>) response.get("choices");if(choices!=null&&!choices.isEmpty())
    {
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return (String) message.get("content");
    }return"No response from ChatGPT.";
}}
