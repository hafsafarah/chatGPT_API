package com.internship.chatgpt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;
    private String answer;
}
