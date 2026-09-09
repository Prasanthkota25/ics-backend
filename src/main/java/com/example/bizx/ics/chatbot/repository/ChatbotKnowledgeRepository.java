package com.example.bizx.ics.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bizx.ics.chatbot.entity.ChatbotKnowledge;

@Repository
public interface ChatbotKnowledgeRepository extends JpaRepository<ChatbotKnowledge, Long> {

	ChatbotKnowledge findByKeywordIgnoreCase(String keyword);

}