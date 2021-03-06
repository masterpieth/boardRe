package com.board.re.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.re.dao.BoardDAO;
import com.board.re.dao.PageMaker;
import com.board.re.vo.BoardVO;
import com.board.re.vo.Criteria;
import com.board.re.vo.ReplyVO;

@Controller
@RequestMapping("/board/**")
public class BoardController {

	@Autowired
	BoardDAO dao;
	
	@GetMapping("/boardList")
	public String boardList(Model model, Criteria cri) {
		ArrayList<BoardVO> list = dao.boardList(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(dao.totalCount());
		model.addAttribute("boardList", list);
		model.addAttribute("pageMaker", pageMaker);
		return "board/boardList";
	}
	
	@GetMapping("/writeBoardForm")
	public String writeBoardForm() {
		return "board/writeBoardForm";
	}
	@PostMapping("/writeBoard")
	public String writeBoard(BoardVO vo, RedirectAttributes rttr, HttpSession session, MultipartFile uploadFile) {
		boolean result = false;
		if(dao.writeBoard(vo, session, uploadFile) > 0) result = true;
		rttr.addFlashAttribute("writeResult", result);
		return "redirect:/board/boardList";
	}
	@GetMapping("/readBoard")
	public String readBoard(BoardVO vo, HttpSession session, Model model) {
		BoardVO result = dao.readBoard(vo, session);
		model.addAttribute("board", result);
		return "board/readBoard";
	}
	
	@GetMapping("/updateBoardForm")
	public String updateBoardForm (BoardVO vo, HttpSession session, Model model) {
		BoardVO result = dao.readBoard(vo, session);
		model.addAttribute("board", result);
		return "board/updateBoardForm";
	}
	@PostMapping("/updateBoard")
	public String updateBoard(BoardVO vo, HttpSession session, RedirectAttributes rttr, MultipartFile uploadFile) {
		boolean result = false;
		if(dao.updateBoard(vo, session, uploadFile) > 0) result = true;
		rttr.addFlashAttribute("updateResult", result);
		return "redirect:/board/readBoard?boardnum="+vo.getBoardnum();
	}
	@GetMapping("/deleteBoard")
	public String deleteBoard(HttpSession session, BoardVO vo, RedirectAttributes rttr) {
		boolean result = false;
		if(dao.deleteBoard(vo, session) > 0) result = true;
		rttr.addFlashAttribute("deleteResult", result);
		return "redirect:/board/boardList";
	}
	@GetMapping("/download")
	public void download(BoardVO vo, HttpServletResponse response, HttpSession session) {
		dao.download(vo, response, session);
	}
}
