package todo_app.service;

import java.util.List;

import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.response.TaskResponseDto;

public interface TaskService {
	void createTask(TaskRequestDto dto);
	List<TaskResponseDto> getAllTask(String userId);
	TaskResponseDto getTaskById(long id, String userId);
	List<TaskResponseDto> filterTaskByFinish(String finish);
	void updateTask(Long id, String userId, TaskRequestDto dto);
	void deleteTask(Long id, String userId);
}
