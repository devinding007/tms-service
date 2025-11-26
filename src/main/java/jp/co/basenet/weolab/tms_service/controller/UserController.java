package jp.co.basenet.weolab.tms_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.co.basenet.weolab.tms_service.common.service.ChatGptService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Tag(name = "ユーザーAPI", description = "ユーザー関連の操作")
public class UserController {

    final ChatGptService gptService;

    public UserController(ChatGptService gptService) {
        this.gptService = gptService;

    }

    @GetMapping("/users/{id}")
    @Operation(summary = "ユーザー取得", description = "IDでユーザー情報を取得します")
    public User getUser(@PathVariable Long id) {
        return new User(id, "ユーザー" + id, this.gptService.askChatGpt("お名前は？"));
    }

    // @GetMapping("/generate")
    // public void getMethodName() {
    //     gptService.generateProblem("3", "8", "Java, VueJS, FrontEnd", "", "5");
    // }
    
    public void test() {

    }

    // DTO
    public static class User {
        private Long id;
        private String name;
        private String email;

        public User(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }

        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setEmail(String email) { this.email = email; }
    }
}
