package chapter04;

// 클래스 Book
class Book {
	// 클래스 내부의 데이터(속성, 필드)
	String title; //제목
	String publisher; // 출판사
	int pageNumber; // 책 페이지 수
	
	//클래스 내부의 동작(행동, 메서드)
	void displayInfo() {
		System.out.println(title + " " + publisher);
		return;
		// System.out.println("실행 안됨"); - 해당 코드에 접급할 수 X
	}
	
	// == 생성자 메서드 ==
	// : new 연산자 사용 시 호출 될 메서드
	// > 필드값 초기화에 주로 사용
	// 		>> 생략 시 속성에 기본값이 할당
	//		cf) 기본타입: int(0), boolean(false), char('')
	//			참조타입: null
	
	// +) 사용자 정의 생성자 메서드 생략 시
	//		>> 클래스명과 동일하면서 '매개변수'가 없는 메서드가 생략
	Book () {}
	
	Book (String title, String publisher, int pageNubmer) {
		this.title = title;
		this.publisher = publisher;
		this.pageNumber = pageNubmer;
	}
}

public class D_Object {

	public static void main(String[] args) {
		Book myBook = new Book();
		
		System.out.println(myBook.title);		// null
		System.out.println(myBook.publisher);	// null
		System.out.println(myBook.pageNumber);	// 0
		
		Book anotherBook = new Book("어린왕자", "어린출판", 100);
		Book thirdBook = new Book("백설공주", "백설출판", 100);
		
		System.out.println(anotherBook.title);		// 어린왕자
		System.out.println(thirdBook.publisher); 	// 백설출판
		
		// 메서드 호출
		myBook.displayInfo(); // null null
		anotherBook.displayInfo(); // 어린왕자 어린출판
		
		myBook.title = "개구리왕자";
		myBook.publisher = "개굴출판";
		myBook.pageNumber = 100;
		
		myBook.displayInfo(); // 개구리왕자 개굴출판
		
		anotherBook.title = "콩쥐팥쥐";
		anotherBook.displayInfo();	// 콩쥐팥쥐 어린출판
	}
}
