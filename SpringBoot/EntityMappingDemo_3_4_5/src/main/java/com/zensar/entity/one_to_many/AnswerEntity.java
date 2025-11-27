package com.zensar.entity.one_to_many;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ANSWER")
public class AnswerEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long ansId;
	
	@Column(name="answer")
	private String answer;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="question_id")
	private QuestionEntity question;

	public AnswerEntity() {}
	
	public AnswerEntity(String answer) {
		this.answer = answer;
	}
	public AnswerEntity(String answer, QuestionEntity question) {
		this.answer = answer;
		this.question = question;
	}
	public Long getAnsId() {
		return ansId;
	}
	public void setAnsId(Long ansId) {
		this.ansId = ansId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public QuestionEntity getQuestion() {
		return question;
	}
	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}
	public String toString() {
		return "Answer: " + ansId + " - " + answer;
	}
}
