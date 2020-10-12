package info.thecodinglive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.thecodinglive.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
