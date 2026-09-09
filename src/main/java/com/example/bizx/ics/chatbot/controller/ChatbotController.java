package com.example.bizx.ics.chatbot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bizx.ics.chatbot.dto.ChatRequest;
import com.example.bizx.ics.chatbot.dto.ChatResponse;
import com.example.bizx.ics.chatbot.entity.ChatHistory;
import com.example.bizx.ics.chatbot.repository.ChatHistoryRepository;
import com.example.bizx.ics.chatbot.service.ChatbotService;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {
	private final ChatHistoryRepository chatHistoryRepository;
	private final ChatbotService chatbotService;

	public ChatbotController(ChatbotService chatbotService, ChatHistoryRepository chatHistoryRepository) {

		this.chatbotService = chatbotService;
		this.chatHistoryRepository = chatHistoryRepository;
	}

	@PostMapping("/message")
	public ResponseEntity<ChatResponse> sendMessage(@RequestBody ChatRequest request) {

		return ResponseEntity.ok(chatbotService.reply(request));
	}

	@GetMapping("/history/{username}")
	public List<ChatHistory> history(@PathVariable String username) {

		return chatHistoryRepository.findByUsernameOrderByCreatedAtAsc(username);
	}

	@DeleteMapping("/history/{username}")
	public void clearHistory(@PathVariable String username) {

		chatHistoryRepository.deleteByUsername(username);
	}
}