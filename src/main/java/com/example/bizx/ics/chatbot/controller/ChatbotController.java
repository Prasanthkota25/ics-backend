//package com.example.bizx.ics.chatbot.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.bizx.ics.chatbot.dto.ChatRequest;
//import com.example.bizx.ics.chatbot.dto.ChatResponse;
//import com.example.bizx.ics.chatbot.entity.ChatMessage;
//import com.example.bizx.ics.chatbot.service.ChatbotService;
//
//@RestController
//@RequestMapping("/api/chatbot")
//@CrossOrigin
//public class ChatbotController {
//
//    @Autowired
//    private ChatbotService chatbotService;
//
//    @PostMapping("/send")
//    public ResponseEntity<ChatResponse> sendMessage(
//            @RequestBody ChatRequest request) {
//
//        ChatResponse response =
//                chatbotService.processMessage(
//                        request.getUsername(),
//                        request.getMessage());
//
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/history/{username}")
//    public ResponseEntity<List<ChatMessage>> history(
//            @PathVariable String username) {
//
//        return ResponseEntity.ok(
//                chatbotService.getHistory(username));
//    }
//}}