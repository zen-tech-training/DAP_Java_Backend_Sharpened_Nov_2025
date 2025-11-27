package com.zensar.entity.one_to_many;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="QUESTION_BANK")
public class QuestionEntity {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long qusId;
	
	@Column(name="description")
	private String qusDesc;
	
	@OneToMany(cascade={CascadeType.ALL}, 
			fetch=FetchType.LAZY, mappedBy = "question")
	private List<AnswerEntity> answers;
	
	public QuestionEntity() {}
	
	public QuestionEntity(String qusDesc) {
		this.qusDesc = qusDesc;
	}
	public QuestionEntity(String qusDesc, List<AnswerEntity> answers) {
		this.qusDesc = qusDesc;
		this.answers = answers;
	}

	public Long getQusId() {
		return qusId;
	}

	public void setQusId(Long qusId) {
		this.qusId = qusId;
	}

	public String getQusDesc() {
		return qusDesc;
	}

	public void setQusDesc(String qusDesc) {
		this.qusDesc = qusDesc;
	}

	public List<AnswerEntity> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerEntity> answers) {
		this.answers = answers;
	}
	
	public String toString() {
		return qusId + " - " + qusDesc + " - " + answers;
	}
}
