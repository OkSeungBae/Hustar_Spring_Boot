package info.thecodinglive.contorller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import info.thecodinglive.model.Board;
import info.thecodinglive.repository.BoardRepository;
import info.thecodinglive.service.BoardService;

@RestController
@RequestMapping(value = "/board")
public class BasicContoller {
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/home")
	public ModelAndView Homepage() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("board/home");
		return mv;
	}
	
	@RequestMapping("/form")
	public ModelAndView insertForm() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("board/form");
		return mv;
	}
	@RequestMapping("/list")
	public ModelAndView list(@PageableDefault(size=2,sort="id",direction =Sort.Direction.DESC) 
	Pageable pageable) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("boardList", boardService.findBoardList(pageable));
		mv.setViewName("board/list");
		return mv;
	}
	@GetMapping("")
	public ModelAndView board(@RequestParam(value = "id",defaultValue = "0") Integer id) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("board", boardService.findBoardById(id));
		mv.setViewName("board/form");
		return mv;
	}
	
	//http://localhost:8080/borad/insert
	@PostMapping("/insert")
	public ResponseEntity<Board> insert(@RequestBody Board board){
		boardService.save(board);
		return new ResponseEntity(board,HttpStatus.CREATED);	
	}
	@Transactional
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBoard(@PathVariable int id,@RequestBody Board reqBoard) {
		Board board=boardService.findBoardById(id);
		board.setTitle(reqBoard.getTitle());
		board.setContent(reqBoard.getContent());
		//boardService.save(board);
		return new ResponseEntity<>("{}",HttpStatus.OK);	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBoard(@PathVariable int id){
		try {
		boardService.deleteById(id);
		}catch(Exception e) {
			System.out.println("===>"+e);
		}
		return new ResponseEntity<>("{}",HttpStatus.OK);
	}

}
