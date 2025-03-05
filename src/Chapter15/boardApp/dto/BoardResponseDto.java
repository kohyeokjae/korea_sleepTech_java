package Chapter15.boardApp.dto;

import Chapter15.boardApp.entity.Board;
import lombok.Getter;
import lombok.ToString;

// BoardRequestDto
// : DB 게시판 데이터 중 사용자에게 응답으로 전달할 객체 구조
// - content 값을 제외한 필드를 제공
@Getter
@ToString
public class BoardResponseDto {
	private long id;
	private String title;
	private String author;
	
	public BoardResponseDto(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.author = board.getAuthor();
	}
	
	public static BoardResponseDto forEntity(Board board) {
		return new BoardResponseDto(board);
	}
}