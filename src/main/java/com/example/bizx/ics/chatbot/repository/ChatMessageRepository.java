package com.example.bizx.ics.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bizx.ics.chatbot.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	List<ChatMessage> findByUsernameOrderByCreatedAtAsc(String username);
}