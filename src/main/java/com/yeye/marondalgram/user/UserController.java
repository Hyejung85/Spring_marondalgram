package com.yeye.marondalgram.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marondalgram/user")
public class UserController {
	
	// 로그인
	@GetMapping("/signin_view")
	public String signin() {
		return "marondalgram/user/sign_in";
	}
	
	// 회원 가입
	@GetMapping("/signup_view")
	public String signup() {
		return "marondalgram/user/sign_up";
	}
	
	// 로그아웃
	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/marondalgram/user/signin_view";
	}
}
