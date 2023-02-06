package com.internship.chatgpt.repository;

import com.internship.chatgpt.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
