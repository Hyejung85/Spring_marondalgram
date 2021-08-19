package com.yeye.marondalgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
}
