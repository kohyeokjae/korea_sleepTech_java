package todo_app.controller;

import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.UserResponseDto;
import todo_app.service.UserService;
import todo_app.service.impl.UserServiceImpl;

public class UserController {
	private UserService service;
	
	public UserController() {
		this.service = new UserServiceImpl();
	}
	
	public void userSignUp(UserSignUpRequestDto dto) {
		service.userSignUp(dto);
	}
	
	public UserResponseDto getUserById(String id) {
		UserResponseDto user = service.getUserById(id);
		return user;
	}
	
	public void deleteUser(String id) {
		service.delete(id);
	}
}
