package com.example.jpa_string_boot_2.question;


import com.example.jpa_string_boot_2.answer.Answer;
import com.example.jpa_string_boot_2.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id // 기본 key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 알아서 증가하는 고유 키 지정
    private int id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    // mappedBy : answer 에서 전달한 question(Question 항목)을 mappedBy를 통해 Question 객체에서 받는다
    // cascade : 질문과 댓글은 OneToMany 관계이다. Question 하나가 삭제되면 그에 해당하는 댓글전체가 삭제하도록 한다.(CascadeType.REMOVE)
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;
}
