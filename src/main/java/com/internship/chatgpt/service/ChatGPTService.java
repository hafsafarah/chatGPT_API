package com.internship.chatgpt.service;

import com.internship.chatgpt.entity.QuestionAnswer;
import com.internship.chatgpt.repository.QuestionAnswerRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {
    @Value("${openai.api_key}")
    private String  API_KEY ;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    private QuestionAnswer chat = new QuestionAnswer();

    private static final Object[] FILE_HEADER = {"Question", "answer"};


    public ChatGPTService(RestTemplate restTemplate, QuestionAnswerRepository questionAnswerRepository) {
        this.restTemplate = restTemplate;
        this.questionAnswerRepository = questionAnswerRepository;
    }

    public Map<String, Object> getCompletion(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 4000);
        requestBody.put("temperature", 1.0);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://api.openai.com/v1/completions",
                entity,
                Map.class
        );
        Map<String, Object> responseBody = response.getBody();
        List<Map<String, Object>> responseList = (List<Map<String, Object>>) responseBody.get("choices");
        String answer = (String) responseList.get(0).get("text");
        writeToCSVFile(prompt, answer);
        chat.setQuestion(prompt);
        chat.setAnswer(answer);
        questionAnswerRepository.save(chat);
        return responseBody;
    }

    private void writeToCSVFile(String question, String answer) {
        try (
                FileWriter fileWriter = new FileWriter("src/main/resources/data/questions_answers.csv", true);
                CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader("Question;answer"))
        ) {
            csvPrinter.printRecord(question, answer);
        } catch (Exception e) {
            System.out.println("Error while writing to CSV file: " + e.getMessage());
        }
    }
}

