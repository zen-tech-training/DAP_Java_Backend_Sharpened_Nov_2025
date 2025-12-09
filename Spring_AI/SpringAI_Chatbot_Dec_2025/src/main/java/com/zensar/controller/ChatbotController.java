package com.zensar.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

	private ChatClient chatClient;
	
	public ChatbotController(ChatClient.Builder builder) {
		ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
		MessageChatMemoryAdvisor messageChatMemoryAdvisor =
				MessageChatMemoryAdvisor.builder(chatMemory).build();
		this.chatClient = builder.defaultAdvisors(messageChatMemoryAdvisor).build();
	}
	
	@GetMapping("/prompt/{prompt}")
	public ResponseEntity<String> sendPrompt(@PathVariable("prompt")String prompt) {
		String promptResponse = chatClient.prompt(prompt).call().content();
		return new ResponseEntity<String>(promptResponse, HttpStatus.OK);
	}
	
}
