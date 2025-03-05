package todo_app;

import java.util.List;
import java.util.Scanner;

import todo_app.controller.TaskController;
import todo_app.controller.UserController;
import todo_app.dto.request.TaskRequestDto;
import todo_app.dto.request.UserSignUpRequestDto;
import todo_app.dto.response.TaskResponseDto;
import todo_app.dto.response.UserResponseDto;

/*
 	=== TODO(할 일) 프로그램 ===
 	
 	1. 프로그램 개요
 	: 할 일(Todo)에 대한 콘솔 기반 응용 프로그램
 	- 두 도메인(사용자, 할 일)을 대상으로 회원가입, 로그인, 로그아웃, 회원 탈퇴 기능
 	  할 일에 대한 추가, 조회(단건/전체), 수정, 삭제 기능을 제공
  	
  	+) 할 일의 활성화 여부(할 일 완료 여부)에 따른 필터링
  	
  	2. 주요 기능 및 비즈니스 로직
  	: 회원 정보 관리 - CR(단건)D (+ 로그인, 로그아웃)
  	: 할 일 기록 관리 - CR(단건/전체)UD (+ 필터링)
  	
  	3. 비즈니스 로직 구조
  	- Controller: 사용자의 입력을 처리하고 적절한 서비스 메소드를 호출
 	- Service: 핵심 비즈니스 로직을 수행
 	- Repository: 데이터 저장소와의 상호 작용을 담당
 	- DTO: 계층 간 데이터 전송을 위한 객체
 	- Entity: 데이터베이스 테이블과 매핑되는 도메인 객체
 	
 	4. 실행 및 관리
 	- Main Class (App.java): 프로그램의 실행 진입점, 사용자와의 상호작용을 관리하고 전체 흐름을 제어
 */

public class App {
	private static final Scanner SC = new Scanner(System.in);
	private static final UserController CONTROLLER = new UserController();
	private static final TaskController TASK_CONTROLLER = new TaskController();
	
	private static void displayMenu() {
		System.out.println("\n[메뉴 선택]");
		
		System.out.println("1. 회원 등록");
		System.out.println("2. 회원 로그인");
		System.out.println("3. 회원 탈퇴");

		System.out.print("메뉴를 선택하세요: ");
	}
	
	private static void displayTaskMenu() {
		System.out.println("\n[메뉴 선택]");
		
		System.out.println("1. 할 일 등록");
		System.out.println("2. 할 일 전체 조회");		
		System.out.println("3. 할 일 단건 조회");		
		System.out.println("4. 할 일 필터링 조회");
		System.out.println("5. 할 일 수정");
		System.out.println("6. 할 일 삭제");
		System.out.println("7. 로그인 화면으로 돌아기기");

		System.out.print("메뉴를 선택하세요: ");
	}
	
	private static int getChoice() {
		while (!SC.hasNextInt()) {
			System.out.println("숫자를 입력하세요");
			SC.nextLine();
		}
		
		int choice = SC.nextInt();
		SC.nextLine();
		
		return choice;
	}
	
	private static String getInput(String prompt) {
		System.out.print(prompt + ": ");
		return SC.nextLine().trim();
	}
	
	private static UserSignUpRequestDto createUserRequest() {
		UserSignUpRequestDto dto = null;
		
		try {
			String userId = getInput("회원 아이디");
			String password = getInput("회원 비밀번호");
			String passwordCheck = getInput("회원 비밀번호 확인");
			String name = getInput("회원 이름");
			int age = Integer.parseInt(getInput("회원 나이"));
			String gender = getInput("회원 성별");
			
			if (!password.equals(passwordCheck)) {
				throw new Error("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			}
			
			if (userId.equals(CONTROLLER.getUserById(userId).getUserId())) {
				throw new Error("이미 존재하는 아이디입니다.");
			}
			
			dto = new UserSignUpRequestDto(userId, password, passwordCheck, name, age, gender);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return dto;
	}
	
	private static String getIdInput() {
		String input = getInput("아이디를 입력하세요");
		
		return input;
	}
	
	private static String getPWInput() {
		String input = getInput("비밀번호를 입력하세요");
		
		return input;
	}
	
	private static boolean processChoice(int choice) {
		switch (choice) {
		// 환자 관련 기능
		case 1: {
			UserSignUpRequestDto dto = createUserRequest();
			CONTROLLER.userSignUp(dto);
			break;
		}
		case 2: {
			String userId = getIdInput();
			String password = getPWInput();
			UserResponseDto dto = CONTROLLER.getUserById(userId);
			if (dto.getUserId() == "") {
				System.out.println("해당하는 ID의 회원이 없습니다.");
			} else if (userId.equals(dto.getUserId()) && password.equals(dto.getPassword())) {
				System.out.println("로그인을 성공하였습니다.");
				
				boolean a = true;
				while (a) {
					displayTaskMenu();
					
					int num = SC.nextInt();
				    SC.nextLine();
					
					switch (num) {
					case 1: {
						TaskRequestDto taskDto = null;
						
						try {
							String content = getInput("할 일을 입력하세요");
							String finish = getInput("완료여부를 입력하세요");
							
							taskDto = new TaskRequestDto(userId, content, finish);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						TASK_CONTROLLER.createTask(taskDto);
						break;
					}
					case 2: {
						List<TaskResponseDto> taskDto = TASK_CONTROLLER.getAllTask(userId);
						if (taskDto.isEmpty()) {
							System.out.println("할 일이 없습니다.");
						} else {
							taskDto.forEach(System.out::println);
						}
						break;
					}
					case 3: {
						long id = Long.parseLong(getIdInput());
						TaskResponseDto taskDto = TASK_CONTROLLER.getTaskById(id, userId);
						
						if (taskDto == null) {
							System.out.println("해당 ID의 할 일이 없습니다.");
						} else {
							System.out.println(taskDto);
						}
						break;
					}
					case 4: {
						String filterTask = getInput("필터 조건(완료 여부)");
						List<TaskResponseDto> filterTasks =
								TASK_CONTROLLER.filterTaskByFinish(filterTask);
						
						if (filterTasks.isEmpty()) {
							System.out.println("검색 결과가 없습니다.");
						} else {
							filterTasks.forEach(System.out::println);
						}
						
						break;
					}
					case 5: {
						long id = Long.parseLong(getIdInput());
						TaskRequestDto taskDto = null;
						
						try {
							String content = getInput("할 일을 입력하세요");
							String finish = getInput("완료여부를 입력하세요");
							
							taskDto = new TaskRequestDto(userId, content, finish);
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						TASK_CONTROLLER.updateTask(id, userId, taskDto);
						System.out.println("ID: " + id + "의 할 일을 수정했습니다.");
						break;
					}
					case 6: {
						long id = Long.parseLong(getIdInput());
						TASK_CONTROLLER.deleteTask(id, userId);
						System.out.println("ID: " + id + "의 할 일을 삭제했습니다.");
						break;
					}
					case 7:{
						System.out.println("로그인 화면으로 갑니다.");
						a = false;
						break;
					}
					default:
						break;
					}
				}
			}
			break;
		}
		case 3: {
			String userId = getIdInput();
			CONTROLLER.deleteUser(userId);
			break;
		}
		case 4: {
			System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
			return false;
		}
		default: {
			System.out.println("잘못된 선택입니다. 유효한 메뉴를 선택해주세요");
			break;
		}
		}
		return true;
		
	}
	
	public static void main(String[] args) {
		try {
			while (true) {
				displayMenu();
				int choice = getChoice();
				
				if (!processChoice(choice)) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SC.close();
		}
	}
}
