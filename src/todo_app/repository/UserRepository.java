package todo_app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import todo_app.entity.User;

public class UserRepository {
	List<User> users = new ArrayList<User>();
	private static final UserRepository USER_REPOSITORY = new UserRepository();
	
	private UserRepository() {}
	
	public static UserRepository getUserRepository() {
		return USER_REPOSITORY;
	}
	
	// 회원 정보 저장
	public void save(User user) {
		users.add(user);
	}
	
	// 회원 단건 조회
	public Optional<User> findById(String userId) {
		return users.stream()
				.filter(user -> user.getUserId().equals(userId))
				.findFirst();
	}
	
	public void deleteUser(User user) {
		users.remove(user);
	}
}
