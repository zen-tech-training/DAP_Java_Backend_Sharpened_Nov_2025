package com.zensar.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rag")
public class RagController {

	private ChatClient chatClient;
	
	public RagController(ChatClient.Builder builder, VectorStore vectorStore) {
		this.chatClient = builder.defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build()).build();
	}
	
	/*
	public RagController(OllamaChatModel ollamaChatModel) {
		this.chatClient = ChatClient.create(ollamaChatModel);
	}
	*/
	@GetMapping("/prompt/{prompt}")
	public ResponseEntity<String> sendPrompt(@PathVariable("prompt")String prompt) {
		String promptResponse = chatClient.prompt(prompt).call().content();
		return new ResponseEntity<String>(promptResponse, HttpStatus.OK);
	}
	
}
