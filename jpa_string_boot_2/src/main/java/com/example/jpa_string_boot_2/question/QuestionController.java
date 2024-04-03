package com.example.jpa_string_boot_2.question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor // Autowired와 비슷한 개념. test파일에서는 Autowired사용, 메인에서는 RequiredArgsConstructor 사용
@Controller
public class QuestionController {

    private final QuestionRepository questionRepository;
    @GetMapping("/question/list")
    public String list(Model model){
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
