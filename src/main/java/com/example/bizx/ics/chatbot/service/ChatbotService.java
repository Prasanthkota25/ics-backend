//package com.example.bizx.ics.chatbot.service;
//
//import java.util.List;
//
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.bizx.ics.LeaveRepository.LeaveRepository;
//import com.example.bizx.ics.chatbot.dto.ChatResponse;
//import com.example.bizx.ics.chatbot.dto.MenuItem;
//import com.example.bizx.ics.chatbot.entity.ChatMessage;
//import com.example.bizx.ics.chatbot.repository.ChatMessageRepository;
//
//@Service
//public class ChatbotService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private LeaveRepository leaveRepository;
//
//    @Autowired
//    private ChatMessageRepository chatRepository;
//
//    public ChatResponse processMessage(
//            String username,
//            String message) {
//
//        String text = message.toLowerCase().trim();
//
//        User user =
//                userRepository.findByUsername(username);
//
//        ChatResponse response = new ChatResponse();
//
//        if (text.contains("hi")
//                || text.contains("hello")
//                || text.contains("hey")) {
//
//            response.setReply(
//                    "Hi "
//                            + user.getFirstname()
//                            + ", How can I help you today?");
//
//        }
//
//        else if (text.contains("manager")
//                && !text.contains("skip")) {
//
//            response.setReply(
//                    "Manager Name : "
//                            + user.getManagerName()
//                            + "\nManager Id : "
//                            + user.getManagerId()
//                            + "\nManager Email : "
//                            + user.getManagerEmail());
//        }
//
//        else if (text.contains("profile")
//                || text.contains("my details")) {
//
//            response.setType("menu");
//
//            response.setTitle("Profile Options");
//
//            response.setItems(
//                    List.of(
//                            new MenuItem(
//                                    "My Profile",
//                                    "/profile")));
//        }
//
//        else if (text.contains("casual")
//                || text.contains("sick")) {
//
//            double balance =
//                    getLeaveBalance(
//                            username,
//                            "Casual / Sick Leave");
//
//            response.setReply(
//                    "Your Casual / Sick Leave balance is "
//                            + balance
//                            + " Days");
//        }
//
//        else {
//
//            response.setReply(
//                    "Sorry, I don't have information about that.");
//        }
//
//        saveConversation(
//                username,
//                message,
//                response.getReply());
//
//        return response;
//    }
//
//    private void saveConversation(
//            String username,
//            String userMessage,
//            String botReply) {
//
//        chatRepository.save(
//                new ChatMessage(
//                        username,
//                        "USER",
//                        userMessage));
//
//        chatRepository.save(
//                new ChatMessage(
//                        username,
//                        "BOT",
//                        botReply));
//    }
//}}