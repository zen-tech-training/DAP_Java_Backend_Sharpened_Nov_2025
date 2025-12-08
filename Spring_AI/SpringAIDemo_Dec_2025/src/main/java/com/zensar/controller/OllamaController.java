package com.zensar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ollama")
public class OllamaController {

	ChatClient chatClient;
	ListOutputConverter listOutputConverter;
	
	public OllamaController(OllamaChatModel ollamaChatModel) {
		this.chatClient = ChatClient.create(ollamaChatModel);
		this.listOutputConverter = new ListOutputConverter(new DefaultConversionService());
	}

	@GetMapping("/fallback/sports/{sports}")
	public ResponseEntity<String> getSportsInfoWithFallback(@PathVariable("sports")String sports) {
		Message userMessage = new UserMessage(String.format("List of 5 most popular personalities in %s along with their career achievement", sports));
		Message systemMessage = new SystemMessage("""
				Your primary function is to share the information about the sports personalities.
				If someone asks about anything else, you can say you only share about sports category
				""");
		Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
		String response = chatClient.prompt(prompt).call().content();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/convert/country/{country}")
	public ResponseEntity<List<String>> getCitiesByCountryInList(@PathVariable("country")String country) {
		String strPrompt = "List down top five cities by their population from the country {country} {format}";
		PromptTemplate promptTemplate = new PromptTemplate(strPrompt);
		Prompt prompt = promptTemplate.create(Map.of("country", country, "format", listOutputConverter.getFormat()));
		String promptResponse = chatClient.prompt(prompt).call().content();
		List<String> cityList = listOutputConverter.convert(promptResponse);
		return new ResponseEntity<List<String>>(cityList, HttpStatus.OK);
	}
	
	@GetMapping("/country/{country}")
	public ResponseEntity<String> getCitiesByCountry(@PathVariable("country")String country) {
		String strPrompt = "List down top five cities by their population from the country {country}";
		PromptTemplate promptTemplate = new PromptTemplate(strPrompt);
		Prompt prompt = promptTemplate.create(Map.of("country", country));
		String promptResponse = chatClient.prompt(prompt).call().content();
		return new ResponseEntity<String>(promptResponse, HttpStatus.OK);
	}
	
	@GetMapping("/prompt/{prompt}")
	public ResponseEntity<String> sendPrompt(@PathVariable("prompt")String prompt) {
		String promptResponse = chatClient.prompt(prompt).call().content();
		return new ResponseEntity<String>(promptResponse, HttpStatus.OK);
	}
}
