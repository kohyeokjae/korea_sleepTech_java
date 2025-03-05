package todo_app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.response.TaskResponseDto;
import todo_app.entity.Task;
import todo_app.entity.User;
import todo_app.repository.TaskRepository;
import todo_app.repository.UserRepository;
import todo_app.service.TaskService;

public class TaskServiceImpl implements TaskService {
	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	
	private static long currentId = 1;
	
	public TaskServiceImpl() {
		this.userRepository = UserRepository.getUserRepository();
		this.taskRepository = TaskRepository.getTaskRepository();
	}
	
	private Long generatedTaskId() {
		return currentId++;
	}
	
	private void validateUserId(String userId) {
		Optional<User> foundUser = userRepository.findById(userId);
		
		if (!foundUser.isPresent()) {
			throw new IllegalArgumentException("해당 ID의 환자를 찾을 수 없습니다. ID: " + userId);
		}
	}

	@Override
	public void createTask(TaskRequestDto dto) {
		try {
			validateUserId(dto.getUserId());
			
			Task task = new Task(generatedTaskId(), dto.getUserId(), dto.getContent(), dto.getFinish());
			
			taskRepository.save(task);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<TaskResponseDto> getAllTask(String id) {
		List<TaskResponseDto> dtos = null;
		
		try {
			List<Task> tasks = taskRepository.findAll();
			
			dtos = tasks.stream()
					.filter(task -> task.getUserId().contains(id))
					.map(task -> new TaskResponseDto(
							task.getId(), 
							task.getUserId(), 
							task.getContent(), 
							task.getFinish()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dtos;
	}

	@Override
	public List<TaskResponseDto> filterTaskByFinish(String finish) {
		List<TaskResponseDto> dtos = null;
		
		try {
			List<Task> tasks = taskRepository.findAll();
			dtos = tasks.stream()
					.filter(task -> task.getFinish().contains(finish))
					.map(filterTask -> new TaskResponseDto(
							filterTask.getId(), 
							filterTask.getUserId(), 
							filterTask.getContent(), 
							filterTask.getFinish()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dtos;
	}

	@Override
	public void updateTask(Long id, String userId, TaskRequestDto dto) {
		try {
			Task task = taskRepository.findById(id, userId)
					.orElseThrow(() ->
					new IllegalArgumentException("해당 ID가 존재하지 않습니다."));
			task.setContent(dto.getContent());
			task.setFinish(dto.getFinish());
			
			taskRepository.save(task);		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	@Override
	public void deleteTask(Long id, String userId) {
		try {
			Task task = taskRepository.findById(id, userId)
					.orElseThrow(() ->
					new IllegalArgumentException("해당 ID가 존재하지 않습니다."));
			taskRepository.delete(task);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public TaskResponseDto getTaskById(long id, String userId) {
		TaskResponseDto responseDto = null;
		
		try {
			Task task = taskRepository.findById(id, userId)
					.orElseThrow(() ->
					new IllegalArgumentException("해당 ID을 찾을 수 없습니다."));
			responseDto = new TaskResponseDto(task.getId(), task.getUserId(), task.getContent(), task.getFinish());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return responseDto;
	}

}
