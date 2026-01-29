package com.zeus.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.domain.Board;
import com.zeus.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@MapperScan(basePackages = "com.zeus.mapper")
@RequestMapping("/board")
public class BoardController {
	@Autowired //(required = false)없으면 NULL을 넣어달라는 뜻 //필드 기반 의존성 주입
	private BoardService boardService;
	
	/*
	@Autowired//생성자 기반 의존성 주입
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	
	@Autowired 세터 기반 의존성주입
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	 */
	
	@GetMapping("/insertForm")
	public String boaradInsertForm(Model model) {
		return "board/insertForm";
	}
	@PostMapping("/insert")
	public String boaradInsert(Board board, Model model) {
		log.info("insert board = " + board.toString());
		try {
			boardService.create(board);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "%s 님의 게시글 작성 삭제 실패되었습니다.".formatted(board.getWriter()));
			return "board/failed";
			}
		model.addAttribute("message", "%s 님의 게시글이 삭제되었습니다.".formatted(board.getWriter()));
		return"board/success";
	}
	@GetMapping("/boardList")
	public String boaradList(Model model) {
		log.info("boaradList");
		try {
			List<Board> boardList = boardService.list();
			model.addAttribute("boardList", boardList);
		} catch (Exception e) {e.printStackTrace();}
		return "board/boardList";
	}
	@GetMapping("/detail")
	public String boaradList(Board b,Model model) {
		log.info("boaradDetail board = " + b.toString());
		try {
			Board board = boardService.read(b);
			if(board == null) {
				model.addAttribute("message", "%d 님의 상세정보를 찾는데 실패했습니다.".formatted(b.getNo()));
				return "board/failed";
			}
			model.addAttribute("board", board);
		} catch (Exception e) {e.printStackTrace();}
		return "board/detail";
	}
	@GetMapping("/delete")
	public String boaradDelete(Board b,Model model) {
		log.info("boaradDelete board = " + b.toString());
		try {
			boardService.delete(b);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "%d 님의 정보가 삭제 실패되었습니다.".formatted(b.getNo()));
			return "board/failed";
			}
		model.addAttribute("message", "%d 님의 정보가 삭제되었습니다.".formatted(b.getNo()));
		return "board/success";
	}
	@GetMapping("/updateForm")
	public String boaradUpdateForm(Board b,Model model) {
		log.info("boaradUpdateForm board = " + b.toString());
		try {
			Board board = boardService.read(b);
			if(board == null) {
				model.addAttribute("message", "%d 님의 정보가 없습니다.".formatted(b.getNo()));
				return "board/failed";
			}
			model.addAttribute("board", board);
		} catch (Exception e) {e.printStackTrace();}
		return "board/updateForm";
	}
	@PostMapping("/update")
	public String boaradUpdate(Board board, Model model) {
		
		log.info("boaradUpdate board = " + board.toString());
		try {
			boardService.update(board);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "%s 님의 게시글 수정이 실패되었습니다.".formatted(board.getWriter()));
			return "board/failed";
			}
		model.addAttribute("message", "%s 님의 게시글 수정 성공입니다.".formatted(board.getWriter()));
		return "board/success";
	}
	@GetMapping("/search")
	public String search(Board board, Model model) {
		log.info("boaradSearch  = "+ board.toString());
		try {
			List<Board> boardList = boardService.search(board);
			model.addAttribute("boardList", boardList);
		} catch (Exception e) {e.printStackTrace();}
		return "board/boardList";
	}
}
