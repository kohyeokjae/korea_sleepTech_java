package todo_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import phr_solution.dto.response.PatientResponseDto;
import phr_solution.entity.Patient;
import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.UserResponseDto;
import todo_app.entity.User;
import todo_app.repository.UserRepository;
import todo_app.service.UserService;

public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	
	public UserServiceImpl() {
		this.repository = UserRepository.getUserRepository();
	}

	@Override
	public void userSignUp(UserSignUpRequestDto dto) {
		User user = new User(dto.getUserId(), dto.getPassword(), dto.getName(), dto.getAge(), dto.getGender());
		repository.save(user);
	}

	@Override
	public UserResponseDto getUserById(String userId) {
		UserResponseDto responseDto = new UserResponseDto("", "");
		
		try {
			User user = repository.findById(userId)
					.orElseThrow(() ->
					new IllegalArgumentException(""));
			responseDto = new UserResponseDto(user.getUserId(), user.getPassword());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseDto;
	}

	@Override
	public void delete(String userId) {
		try {
			User user = repository.findById(userId)
					.orElseThrow(() ->
					new IllegalArgumentException("해당 ID를 가진 사용자를 찾을 수 없습니다."));
			
			repository.deleteUser(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
