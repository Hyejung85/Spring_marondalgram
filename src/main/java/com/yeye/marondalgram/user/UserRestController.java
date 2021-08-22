package com.yeye.marondalgram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yeye.marondalgram.user.bo.UserBO;
import com.yeye.marondalgram.user.model.User;

@RestController
@RequestMapping("/marondalgram/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 회원가입
	@RequestMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email){
		
		Map<String, String> result = new HashMap<>();
		int count = userBO.signUp(loginId, password, name, email);
		
		if(count == 1) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	// 아이디 중복확인
	@GetMapping("/is_duplicate_id")
	public Map<String, Boolean> isDuplicateId(
			@RequestParam("loginId") String loginId){
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(userBO.isDuplicateId(loginId)) {
			result.put("is_duplicate", true);
		}else {
			result.put("is_duplicate", false);
		}
		return result;
	}
	
	// 로그인
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request){
		
		Map<String, String> result = new HashMap<>();
		User user = userBO.signIn(loginId, password);
		
		if(user != null) {
			result.put("result", "success");
			
			//로그인 상태 세션 가져오기
			HttpSession session = request.getSession();
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		}else {
			result.put("result", "fail");
		}
		return result;
	}
	
}
