package todo_app.service;

import java.util.List;

import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.UserResponseDto;
import todo_app.entity.User;

public interface UserService {
	void userSignUp(UserSignUpRequestDto dto);
	UserResponseDto getUserById(String userId);
	void delete(String userId);
}
