package chapter10;

import java.util.ArrayList;
import java.util.LinkedList;

// === 이벤트 관리 시스템 ===
// - 이벤트 참가자 명단과 대기열 관리를 위한 시스템
// : 참가자는 사전 등록된 명단을 통해 이벤트에 참가
// : 명단이 가득 찰 경우 대기열로 추가, 참가자가 떠나면 대기열의 다음 사람이 추가

// 1) 이벤트 참가자 명단 관리: 사전 등록, 이벤트 당일 추가 참가자 등록 X
// : ArrayList (목록 추가, 삭제 X / 참가자 조회 X)

// 2) 대기열 관리
// : 이벤트의 한정된 좌석, 좌석이 모두 차면 추가 참가자는 대기열에 등록
// : LinkedList (목록 추가, 삭제 O / 참가자 조회 X)

class EventManagement {
	// == 필드 ==
	ArrayList<String> participantList = new ArrayList<>();	// 이벤트 참가자 명단
	LinkedList<String> waitingQueue = new LinkedList<>();	// 대기열 명단
	
	// == 메서드 ==
	void addParticipant(String name) {
		participantList.add(name);
	}
	
	void addToWaitingQueue(String name) {
		waitingQueue.add(name);
	}
	
	void leaveParticipant(String name) {
		// 대기열의 인원이 존재해야만 대기열 인원을 참가자 명단에 추가 가능
		// : 대기열 크기(.size())가 0 이상
		if (waitingQueue.size() > 0) {
			String nextParicipant = waitingQueue.remove(0);	// 대기열에서 제거
			addParticipant(nextParicipant);	// 참가자 명단에 추가
		}
	}
	
	boolean checkParticipant(String name) {
		return participantList.contains(name);
	}
}

public class C_Practice {

	public static void main(String[] args) {
		EventManagement eventManagement = new EventManagement();
		
		eventManagement.addParticipant("홍길동");
		eventManagement.addParticipant("홍길둥");
		eventManagement.addParticipant("홍길똥");
		eventManagement.addParticipant("홍길덩");
		eventManagement.addParticipant("홍길당");
		
		eventManagement.addToWaitingQueue("고길동");
		eventManagement.addToWaitingQueue("고길덩");
		eventManagement.addToWaitingQueue("고길둥");
		eventManagement.addToWaitingQueue("고길당");
		
		System.out.println(eventManagement.participantList);	// [홍길동, 홍길둥, 홍길똥, 홍길덩, 홍길당]
		System.out.println(eventManagement.waitingQueue);	// [고길동, 고길덩, 고길둥, 고길당]
		
		eventManagement.addToWaitingQueue("고길똥");
		eventManagement.addToWaitingQueue("고길떵");
		
		eventManagement.leaveParticipant("홍길동");
		eventManagement.leaveParticipant("홍길둥");
		eventManagement.leaveParticipant("홍길똥");
		
		System.out.println(eventManagement.participantList);
		// [홍길동, 홍길둥, 홍길똥, 홍길덩, 홍길당, 고길동, 고길덩, 고길둥]
		
		System.out.println(eventManagement.waitingQueue);	// [고길당, 고길똥, 고길떵]
		
		System.out.println(eventManagement.checkParticipant("홍길동"));	// true
		System.out.println(eventManagement.checkParticipant("고길당"));	// false
		
	}

}
