package com.example.jpa_string_boot_2.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject); // subject로 질문 찾기
    Question findBySubjectAndContent(String subject, String content); // subject와 content로 찾기
    List<Question> findBySubjectLike(String subject); // 특정 문자열로 subject 찾기
}
