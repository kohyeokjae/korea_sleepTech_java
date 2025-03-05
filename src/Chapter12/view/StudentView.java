package chapter12.view;

import java.util.List;
import java.util.Scanner;

import chapter12.controller.StudentController;
import chapter12.model.Student;

public class StudentView {
	private final Scanner sc;
	private final StudentController controller;
	
	public StudentView() {
		this.sc = new Scanner(System.in);
		this.controller = new StudentController();
	}
	
	public void ShowMenu() {
		int choice = 0;
		
		while (true) {
			System.out.println("== Student Management System ==");
			System.out.println("1. 학생 추가");
			System.out.println("2. 학생 전체 조회");
			System.out.println("3. 학생 단건 조회");
			System.out.println("4. 학생 수정");
			System.out.println("5. 학생 삭제");
			System.out.println("6. 프로그램 종료");
			
			choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				addStudentView();
				break;
			case 2:
				showAllStudentView();
				break;
			case 3:
				findStudentView();
				break;
			case 4:
				updaetStudentView();
				break;
			case 5:
				deleteStudentView();
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				return;
			default :
				System.out.println("유효하지 않은 메뉴입니다. 다시 선택해주세요");
			}
		}
	}
	
	private void addStudentView() {
		System.out.println("학생 이름: ");
		String name = sc.nextLine();
		System.out.println("학생 나이: ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("학생 번호: ");
		String studentId = sc.nextLine();
		
		controller.addStudent(name, age, studentId);
		System.out.println("학생 추가 완료");
	}
	
	private void showAllStudentView() {
		List<Student> students = controller.getAllStudents();
		
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	private void findStudentView() {
		System.out.println("학생 번호: ");
		String studentId = sc.nextLine();
		
		Student student = controller.findStudentByStudentId(studentId);
		if (student != null) {
			System.out.println(student);
		} else {
			System.out.println("해당 ID의 학생이 없습니다.");
		}
	}
	
	private void updaetStudentView() {
		System.out.println("새로운 학생 이름: ");
		String newName = sc.nextLine();
		System.out.println("새로운 학생 나이: ");
		int newAge = Integer.parseInt(sc.nextLine());
		System.out.println("학생 번호: ");
		String id = sc.nextLine();
		
		Boolean result = controller.updateStudent(id, newName, newAge);
		
		if (result) {
			System.out.println("학생 수정 완료");
		} else {
			System.out.println("해당 ID의 학생을 찾을 수 없습니다.");
		}
	}
	
	private void deleteStudentView() {
		System.out.println("학생 번호: ");
		String id = sc.nextLine();
		
		Boolean result = controller.removeStudent(id);
		
		if (result) {
			System.out.println("학생 삭제 완료");
		} else {
			System.out.println("해당 ID의 학생을 찾을 수 없습니다.");
		}
	}
}
