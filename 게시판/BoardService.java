package info.thecodinglive.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import info.thecodinglive.repository.BoardRepository;
import info.thecodinglive.model.*;
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository=boardRepository;
	}
	public Page<Board> findBoardList(Pageable pageable){
		pageable=PageRequest.of(pageable.getPageNumber()<=0?0:pageable.getPageNumber()-1
				, pageable.getPageSize(), pageable.getSort());
		return boardRepository.findAll(pageable);
	}
	
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	public Board findBoardById(Integer id) {
		return boardRepository.findById(id).orElse(new Board());
	}
	public void deleteById(Integer id) {
		boardRepository.deleteById(id);
	}

}
