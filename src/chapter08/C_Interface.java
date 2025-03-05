package chapter08;

// 인터페이스 명명규칙
// : 클래스와 동일 - UpperCamelCase
interface Flyable {
	int AVERAGE_SPEED = 100;	// public static final 생략
	
	void fly();	// 추상 메소드 - public abstract 생략
	
	// 해당 인터페이스를 구현하는 클래스에서 재정의 가능
	default void land() {
		System.out.println("착륙합니다.");
	}
	
	// 인터페이스 호출만 가능! - 재정의 불가능
	static int getWingCount() {
		return 2;
	}
}

class Sparrow implements Flyable {
	public void fly() {
		System.out.println("참새의 속력은 " + AVERAGE_SPEED + "km의 평균 속도보다 느립니다.");
	}
	
	@Override
	public void land() {
		System.out.println("사뿐");
	}
}

class Eagle	implements Flyable {
	public void fly() {
		System.out.println("독수리의 속력은 " + AVERAGE_SPEED + "km의 평균 속도보다 빠르다.");
	}
	
	@Override
	public void land() {
		System.out.println("슈우웅");
	}
}

public class C_Interface {

	public static void main(String[] args) {
		Sparrow bird1 = new Sparrow();
		
		bird1.fly();
		bird1.land();
		
		Eagle bird2 = new Eagle();
		
		bird2.fly();
		bird2.land();
		
		System.out.println(Flyable.getWingCount());	// 2
	}

}
