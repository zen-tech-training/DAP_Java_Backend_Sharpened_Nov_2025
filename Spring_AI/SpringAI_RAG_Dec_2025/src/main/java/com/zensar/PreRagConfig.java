package com.zensar;

import java.io.File;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class PreRagConfig {

	@Value("classpath:/Budget_Speech.txt")
	private Resource budget;
	
	@Bean
	public SimpleVectorStore buildVectorStore(EmbeddingModel embeddingModel) {
		SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
		
		File vectorStoreFile = 
				new File("D:\\Training\\Ongoing_trainings\\DAP_Java_Backend_Sharpened_Nov_2025\\Workspace\\SpringAI_RAG_Dec_2025\\src\\main\\resources\\vector_store.json");
		
		if(vectorStoreFile.exists()) { //Vector file already created
			simpleVectorStore.load(vectorStoreFile);
			System.out.println("Vector store loaded");
		}
		else { //converts txt file into vector database
			System.out.println("Converting text into vector db started..");
			TextReader textReader = new TextReader(budget);
			textReader.getCustomMetadata().put("filename", "Budget_Speech.txt");
			List<Document> documents = textReader.get();
			simpleVectorStore.add(documents);
			simpleVectorStore.save(vectorStoreFile);
			System.out.println("Text converted into vector db..");
		}
		return simpleVectorStore;
	}
}
