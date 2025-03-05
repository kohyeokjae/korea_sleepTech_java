package todo_app.controller;

import java.util.List;

import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.response.TaskResponseDto;
import todo_app.service.TaskService;
import todo_app.service.impl.TaskServiceImpl;

public class TaskController {
	private TaskService service;
	
	public TaskController() {
		this.service = new TaskServiceImpl();
	}
	
	public void createTask(TaskRequestDto dto) {
		service.createTask(dto);
	}
	
	public List<TaskResponseDto> getAllTask(String userId) {
		return service.getAllTask(userId);
	}
	
	public TaskResponseDto getTaskById(long id, String userId) {
		TaskResponseDto task = service.getTaskById(id, userId);
		return task;
	}
	
	public List<TaskResponseDto> filterTaskByFinish(String finish) {
		return service.filterTaskByFinish(finish);
	}
	
	public void updateTask(long id, String userId, TaskRequestDto dto) {
		service.updateTask(id, userId, dto);
	}
	
	public void deleteTask(long id, String userId) {
		service.deleteTask(id, userId);
	}
}
