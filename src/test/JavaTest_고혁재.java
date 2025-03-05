package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class Student {
	private String name;
	private int score;
	
	Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
}

public class JavaTest_고혁재 {

	public static void main(String[] args) {
		// cf) 답안 작성 예시

		// 1. 서술형
		// - 기본 데이터 타입:
		// 기본 데이터 타입은 실제 데이터가 저장
		// 정수: byte, short, int, long
		// 문자: char
		// 실수: float, double
		// 논리: boolean
		
		// - 참조 데이터 타입:
		// 메모리 주소를 저장
		// 문자열: String
		// 배열: Array
		// List, Map
		
		// 2. 코드 구현 문제
		int num1;	// 이후 풀이 작성
		num1 = 10;
		
		double num2 = 3.5;
		
		double result = num2 + num1;
		System.out.println(result); // 13.5
		
		// 3. 코드 구현 문제
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자 입력: ");
		int number = sc.nextInt();
		
		if (number % 2 == 0) {
			System.out.println("입력하신 숫자는 짝수입니다.");
		} else {
			System.out.println("입력하신 숫자는 홀수입니다.");
		}
		
		sc.close();
		
		// 4. 코드 구현 문제
		int[] numbers = {1, 2, 3, 4, 5};
		
		for (int i : numbers) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		// 5. 코드 구현 문제
		List<Student> students = Arrays.asList(
				new Student("John", 85),
				new Student("Jane", 92),
				new Student("Tom", 78),
				new Student("Emily", 88),
				new Student("Alex", 95)
				);
		
		List<Student> name = students.stream()
				.filter(e -> e.getScore() >= 90)
				.collect(Collectors.toList());
		name.forEach(e -> System.out.println(e.getName()));
		
		
		// 6. 단답형
		// Object
		
		// 7. 단답형
		// 
		
		// 8. 단답형
		// switch
		
		// 9. 다음 중 인터페이스에서 사용할 수 없는 것은 무엇입니까?
		// 3. private 메서드
		
		// 10. 다음 중 @FunctionalInterface에 해당하는 조건으로 올바른 것은 무엇입니까?
		// 1. 여러 개의 추상 메서드 포함 가능
		
		// 11. 다음 코드에서 출력 결과는 무엇입니까?
		// Child
		
		// 12. 싱글톤 패턴을 구현할 때 가장 일반적으로 사용되는 메서드는 무엇입니까?
		// 수업못들어서 아직 정리가 안됨
		
		// 13. 추상 클래스
		// 2. 아니요
		
		// 14. 다운 캐스팅
		// 
		
		// 15. 빌더 패턴의 주요 이점은 무엇입니까? 
		//
		
		// 16. MVC 패턴에서 사용자의 입력을 처리하는 역할은 무엇입니까?
		// 3. Controller
		
		// 17. 인터페이스의 모든 메서드는 기본적으로 어떤 접근 제어자를 가집니까?
		// public abstract
		
		// 18. 추상 클래스와 인터페이스의 주요 차이점 중 맞지 않는 것은 무엇입니까?
		// 3. 터페이스는 필드를 가질 수 없다.
		
	}
}
