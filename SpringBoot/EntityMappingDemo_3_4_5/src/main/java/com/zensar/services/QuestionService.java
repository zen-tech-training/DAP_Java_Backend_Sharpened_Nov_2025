package com.zensar.services;

import java.util.List;

import com.zensar.dto.Question;

public interface QuestionService {
	public Question createQuestion(Question question);
	public List<Question> getAllQuestions();
}
