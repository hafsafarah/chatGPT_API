package com.internship.chatgpt.web;

import com.internship.chatgpt.entity.QuestionAnswer;
import com.internship.chatgpt.repository.QuestionAnswerRepository;
import com.internship.chatgpt.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;
    @Autowired
    QuestionAnswerRepository chat;

    @PostMapping("/completion")
    public Map<String, Object> getCompletion(@RequestBody String prompt) {
        return chatGPTService.getCompletion(prompt);


    }
}