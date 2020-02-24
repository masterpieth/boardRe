package com.board.re.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.re.dao.MemberDAO;
import com.board.re.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	MemberDAO dao;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	@PostMapping("/login")
	public String login(MemberVO vo, HttpSession session) {
		if(dao.login(vo, session) != null) return "redirect:/";
		return "redirect:/loginForm";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		dao.logout(session);
		return "redirect:/";
	}
	@GetMapping("/signupForm")
	public String signupForm() {
		return "member/signupForm";
	}
	@PostMapping("/signup")
	public String signup(MemberVO vo, RedirectAttributes rttr) {
		boolean result = false;
		if(dao.addMember(vo) > 0) result = true;
		rttr.addFlashAttribute("signupResult", result);
		return "redirect:/";
	}
	@GetMapping("/idCheckForm")
	public String idCheckForm() {
		return "member/idCheck";
	}
	@PostMapping("/idCheck")
	public String idCheck(MemberVO vo, RedirectAttributes rttr) {
		boolean result = false;
		if(dao.readMember(vo) != null) result = true;
		rttr.addFlashAttribute("userid", vo.getUserid());
		rttr.addFlashAttribute("idCheckResult", result);
		return "redirect:/member/idCheckForm";
	}
}
