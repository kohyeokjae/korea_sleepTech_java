package Chapter15.boardApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Chapter15.boardApp.dto.BoardRequestDto;
import Chapter15.boardApp.dto.BoardResponseDto;
import Chapter15.boardApp.entity.Board;
import Chapter15.boardApp.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {
	private final BoardRepository repository;
	
	public BoardServiceImpl() {
		this.repository = new BoardRepository();
	}
	
	@Override
	public void createBoard(BoardRequestDto dto) {
		Board board = dto.toEntity(); // dto 객체에서 인스턴스 메서드 호출
		repository.save(board);
	}

	@Override
	public List<BoardResponseDto> findAllBoards() {
		List<Board> boardList = repository.findAll();
		
		// 게시판 데이터를 응답 객체로 변환
		List<BoardResponseDto> boardResponseDtos = boardList.stream()
				.map(BoardResponseDto::forEntity)
				// .map(dto -> BoardResponseDto.forEntity(dto))
				.collect(Collectors.toList());
		
		return boardResponseDtos;
	}

	@Override
	public BoardResponseDto findBoardById(long id) {
		Optional<Board> board = repository.findById(id);
												
		BoardResponseDto barBoardResponseDto = board.map(BoardResponseDto::forEntity)
				.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
		
		return barBoardResponseDto;
	}

	@Override
	public void updateBoard(long id, BoardRequestDto dto) {
		Board existingBoard = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
		
		existingBoard.setTitle(dto.getTitle());
		existingBoard.setContent(dto.getContent());
	}

	@Override
	public void deleteBoard(long id) {
		if (!repository.findById(id).isPresent()) {
			// 해당 id의 데이터가 존재하지 X
			throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
		}
		repository.delete(id);
	}

}
