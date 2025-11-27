package com.zensar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.dto.Question;
import com.zensar.entity.one_to_many.QuestionEntity;
import com.zensar.repo.QuestionRepo;
import com.zensar.util.QuestionUtil;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepo questionRepo;
	
	@Override
	public Question createQuestion(Question question) {
		QuestionEntity questionEntity = questionRepo.save(QuestionUtil.convertQuestionIntoQuestionEntity(question));
		return QuestionUtil.convertQuestionEntityIntoQuestion(questionEntity);
	}

	@Override
	public List<Question> getAllQuestions() {
		return QuestionUtil.convertQuestionEntityListIntoQuestionList(questionRepo.findAll());
	}

}
