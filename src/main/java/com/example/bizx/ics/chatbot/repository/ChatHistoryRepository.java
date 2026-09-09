package com.example.bizx.ics.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bizx.ics.chatbot.entity.ChatHistory;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

	List<ChatHistory> findByUsernameOrderByCreatedAtAsc(String username);

	void deleteByUsername(String username);
}