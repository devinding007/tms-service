package jp.co.basenet.weolab.tms_service.common.repository;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class PromptTemplateRepository {

    private final Map<String, String> templates = new ConcurrentHashMap<>();

    public PromptTemplateRepository(
            ResourcePatternResolver resolver
    ) throws Exception {

        // classpath:prompts/*.md を全部読む
        Resource[] resources = resolver.getResources("classpath:gpt-prompts/*.md");

        for (Resource r : resources) {
            String filename = r.getFilename(); // e.g. "generate-exam-question.md"
            if (filename == null) continue;

            String key = filename.replace(".md", ""); // "generate-exam-question"

            String text;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(r.getInputStream(), StandardCharsets.UTF_8))) {

                text = br.lines().collect(Collectors.joining("\n"));
                templates.put(key, text);
            }

        }
    }

    // 呼び出し側が使うメソッド
    public String render(String templateName, Map<String, String> model) {
        String template = templates.get(templateName);
        if (template == null) {
            throw new IllegalArgumentException("No such template: " + templateName);
        }
        for(var entry : model.entrySet()) {
            template = template.replace(String.format("{{%s}}", entry.getKey()), entry.getValue());
        }
        return template;
    }
}
