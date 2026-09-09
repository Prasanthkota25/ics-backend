package com.example.bizx.ics.chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bizx.ics.LeaveEntity.LeaveRequest;
import com.example.bizx.ics.LeaveEntity.LeaveType;
import com.example.bizx.ics.LeaveRepository.LeaveRepository;
import com.example.bizx.ics.LeaveRepository.LeaveTypeRepository;
import com.example.bizx.ics.UserEntity.LoginEntity;
import com.example.bizx.ics.UserRepository.LoginRepository;
import com.example.bizx.ics.chatbot.dto.ChatRequest;
import com.example.bizx.ics.chatbot.dto.ChatResponse;
import com.example.bizx.ics.chatbot.entity.ChatHistory;
import com.example.bizx.ics.chatbot.repository.ChatHistoryRepository;

@Service
public class ChatbotService {
	private final LoginRepository loginRepository;
	private final LeaveRepository leaveRepository;
	private final LeaveTypeRepository leaveTypeRepository;
	private final ChatHistoryRepository chatHistoryRepository;

	public ChatbotService(LoginRepository loginRepository, LeaveRepository leaveRepository,
			LeaveTypeRepository leaveTypeRepository, ChatHistoryRepository chatHistoryRepository) {

		this.loginRepository = loginRepository;
		this.leaveRepository = leaveRepository;
		this.leaveTypeRepository = leaveTypeRepository;
		this.chatHistoryRepository = chatHistoryRepository;
	}

	private double getLeaveBalance(LoginEntity user, String leaveType, double opening) {

		List<LeaveRequest> leaves = leaveRepository.findByUserId(user.getId());

		double used = leaves.stream().filter(l -> leaveType.equalsIgnoreCase(l.getLeaveType()))
				.filter(l -> "APPROVED".equalsIgnoreCase(l.getStatus()) || "PENDING".equalsIgnoreCase(l.getStatus())
						|| "APPLIED".equalsIgnoreCase(l.getStatus()))
				.mapToDouble(LeaveRequest::getDays).sum();

		return Math.max(0, opening - used);
	}

	public ChatResponse reply(ChatRequest request) {

		ChatResponse response = new ChatResponse();

		String username = request.getUsername();

		String message = request.getMessage().trim().toLowerCase();
		System.out.println("Processed Message = [" + message + "]");

		System.out.println("Username =prasanth " + request.getUsername());
		System.out.println("Message = " + request.getMessage());
		LoginEntity user = loginRepository.findByUsername(username);
		if (user == null) {

			response.setType("text");
			response.setReply("User not found");
			return response;
		}

		response.setType("text");
		if (message.contains("hi") || message.contains("hello") || message.contains("hey")) {

			response.setReply("Hi " + user.getFirstname() + " " + user.getLastname() + ", How can I help you today?");

			saveHistory(request, response);

			return response;
		}
		if (message.contains("manager") && !message.contains("skip")) {

			response.setReply("Manager : " + user.getManager() + "\nManager ID : " + user.getManagerId());
			saveHistory(request, response);

			return response;
		}
		if (message.contains("profile") || message.contains("my details") || message.contains("employee details")) {

			response.setType("navigation");
			response.setData("/profile");
			response.setReply("Opening Profile Page");

			saveHistory(request, response);

			return response;
		}
		if (message.contains("casual") || message.contains("sick")) {

			List<LeaveRequest> leaves = leaveRepository.findByUserId(user.getId());

			double used = leaves.stream().filter(l -> "Casual / Sick Leave".equalsIgnoreCase(l.getLeaveType()))
					.filter(l -> "APPROVED".equalsIgnoreCase(l.getStatus()) || "PENDING".equalsIgnoreCase(l.getStatus())
							|| "APPLIED".equalsIgnoreCase(l.getStatus()))
					.mapToDouble(LeaveRequest::getDays).sum();

			double opening = 12;

			double balance = opening - used;

			response.setReply("Your Casual / Sick Leave balance is " + balance + " Days");

			saveHistory(request, response);

			return response;
		}
		if (message.contains("paternity")) {

			if (!"MALE".equalsIgnoreCase(user.getGender())) {
				response.setReply("Paternity Leave is not applicable for you.");
				return response;
			}

			double balance = getLeaveBalance(user, "Paternity Leave", 5);

			response.setReply("Your Paternity Leave balance is " + balance + " Days");

			saveHistory(request, response);
			return response;
		}
		if (message.contains("maternity")) {

			if (!"FEMALE".equalsIgnoreCase(user.getGender())) {

				response.setReply("Maternity Leave is not applicable for you.");

				return response;
			}

			response.setReply("Your Maternity Leave balance is 182 Days");

			saveHistory(request, response);

			return response;
		}
		if (message.contains("leave types") || message.contains("eligible leaves")
				|| message.contains("available leaves")) {

			List<LeaveType> leaveTypes = leaveTypeRepository.findAll();

			StringBuilder builder = new StringBuilder();

			builder.append("Eligible Leave Types:\n\n");

			for (LeaveType type : leaveTypes) {

				builder.append("• ").append(type.getName()).append("\n");
			}

			response.setReply(builder.toString());

			saveHistory(request, response);

			return response;
		}
		if (message.contains("privilege")
		        || message.contains("privilege leave")
		        || message.contains("pl")) {

		    System.out.println("Privilege block entered");

		    double balance = getLeaveBalance(user, "Privilege Leave", 13);

		    response.setReply("Your Privilege Leave balance is " + balance + " Days");

		    saveHistory(request, response);
		    return response;
		}
		if (message.contains("leave balance") || message.contains("leave balances") || message.contains("all leaves")
				|| message.contains("balance") || message.contains("show my leave balance")) {

			double cas = getLeaveBalance(user, "Casual / Sick Leave", 12);

			double pri = getLeaveBalance(user, "Privilege Leave", 13);

			double ado = getLeaveBalance(user, "Adoption Leave", "MALE".equalsIgnoreCase(user.getGender()) ? 5 : 84);

			double ber = getLeaveBalance(user, "Bereavement Leave", 3);

			double pat = getLeaveBalance(user, "Paternity Leave", 5);

			response.setReply(
					"Your Leave Balances:<br><br>" + "Privilege Leave : " + pri + " Days<br>" + "Casual / Sick Leave : "
							+ cas + " Days<br>" + "Adoption Leave : " + ado + " Days<br>" + "Bereavement Leave : " + ber
							+ " Days<br>" + "Paternity Leave : " + pat + " Days<br>" + "LOP : 365 Days");

			saveHistory(request, response);
			return response;
		}
		if (message.contains("bereavement")) {

			double balance = getLeaveBalance(user, "Bereavement Leave", 3);

			response.setReply("Your Bereavement Leave balance is " + balance + " Days");

			saveHistory(request, response);
			return response;
		}
		if (message.contains("adoption")) {

			double opening = "MALE".equalsIgnoreCase(user.getGender()) ? 5 : 84;

			double balance = getLeaveBalance(user, "Adoption Leave", opening);

			response.setReply("Your Adoption Leave balance is " + balance + " Days");

			saveHistory(request, response);
			return response;
		}
//		if (message.contains("privilege")) {
//
//			double balance = getLeaveBalance(user, "Privilege Leave", 13);
//
//			response.setReply("Your Privilege Leave balance is " + balance + " Days");
//
//			saveHistory(request, response);
//			return response;
//		}

		if (message.contains("lop")) {

			response.setReply("LOP Balance : 365 Days");

			saveHistory(request, response);
			return response;
		}
		if (message.contains("profile") || message.contains("account") || message.contains("my information")
				|| message.contains("my details") || message.contains("employee details")) {

			response.setType("navigation");
			response.setData("/profile");
			response.setReply("Opening Profile Page");

			saveHistory(request, response);
			return response;
		}
		if (message.contains("which leave can i apply") || message.contains("what leaves can i apply")
				|| message.contains("leave types") || message.contains("eligible leaves")
				|| message.contains("available leaves")) {

			List<LeaveType> leaveTypes = leaveTypeRepository.findAll();

			StringBuilder builder = new StringBuilder();

			builder.append("Eligible Leave Types:\n\n");

			for (LeaveType type : leaveTypes) {

				builder.append("• ").append(type.getName()).append("\n");
			}

			response.setReply(builder.toString());

			saveHistory(request, response);
			return response;
		}
		if (message.contains("manager") && !message.contains("skip")) {

			response.setReply("Manager : " + user.getManager() + "\nManager ID : " + user.getManagerId());

			saveHistory(request, response);
			return response;
		}
		if (message.contains("hi") || message.contains("hello") || message.contains("hey") || message.contains("hlo")) {

			response.setReply("Hi " + user.getFirstname() + ", How can I help you today?");

			saveHistory(request, response);
			return response;
		}

		System.out.println("Default block entered");
		System.out.println("Message received = " + message);

		response.setReply("Sorry, I don't have information about that.");

		saveHistory(request, response);

		return response;
//		response.setReply("Sorry, I don't have information about that.");
//
//		saveHistory(request, response);
//
//		return response;
	}

	private void saveHistory(ChatRequest request, ChatResponse response) {

		ChatHistory history = new ChatHistory();

		history.setUsername(request.getUsername());

		history.setUserMessage(request.getMessage());

		history.setBotResponse(response.getReply());

		chatHistoryRepository.save(history);
	}
}