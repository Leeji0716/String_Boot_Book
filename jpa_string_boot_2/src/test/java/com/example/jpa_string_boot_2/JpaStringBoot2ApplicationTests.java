package com.example.jpa_string_boot_2;

import com.example.jpa_string_boot_2.answer.Answer;
import com.example.jpa_string_boot_2.answer.AnswerRepository;
import com.example.jpa_string_boot_2.question.Question;
import com.example.jpa_string_boot_2.question.QuestionRepository;
import com.example.jpa_string_boot_2.question.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JpaStringBoot2ApplicationTests {

	@Autowired //의존성 주입 (new 하지 않아도 스프링이 알아서 추적하여 추가함)
	QuestionRepository questionRepository;

	@Test
	void test1(){
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);
	}

	@Test
	void test2(){
		List<Question> questionall = this.questionRepository.findAll();
		assertEquals(2, questionall.size());

		Question q = questionall.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	void test3(){
		Optional<Question> op = this.questionRepository.findById(1);
		if (op.isPresent()){
			Question q = op.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}
	@Test
	void test4(){
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}
	@Test
	void test5(){
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고싶습니다.");
		assertEquals(1,q.getId());
	}
	@Test
	void test6(){
		List<Question> questionList = this.questionRepository.findBySubjectLike("%sbb%");
		Question q = questionList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
	@Test
	void test7(){
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}
	@Test
	void test8(){
		assertEquals(2,this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1,this.questionRepository.count());
	}

	@Autowired
	AnswerRepository answerRepository;

	@Test
	void test9(){
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setQuestion(q);
		a.setContent("네 자동으로 생성됩니다.");
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	void test10(){
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}
	@Test
	@Transactional
	void test11(){
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
	@Autowired
	QuestionService questionService;
	@Test
	void testJpa() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content, null);
		}
	}
}
