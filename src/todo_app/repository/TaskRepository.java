package todo_app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import todo_app.entity.Task;

public class TaskRepository {
	// Task 데이터에 대한 List 컬렉션 저장소 정의
	//	할일 등록(save), 할일 단건 조회, 할일 전체 조회, 할일 수정, 할일 삭제

	private List<Task> tasks = new ArrayList<Task>();
	private static final TaskRepository  TASK_REPOSITORY = new TaskRepository();
	
	private TaskRepository() {}

	public static TaskRepository getTaskRepository() {
		return TASK_REPOSITORY;
	}
	
	public void save(Task task) {
		tasks.add(task);
	}
	
	public Optional<Task> findById(Long id, String userId) {
		return tasks.stream()
				.filter(task -> task.getId().equals(id) && task.getUserId().equals(userId))
				.findFirst();
	}
	
	public List<Task> findAll() {
		return tasks;
	}
	
	public void delete(Task task) {
		tasks.remove(task);
	}
}
