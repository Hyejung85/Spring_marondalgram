package com.yeye.marondalgram.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yeye.marondalgram.post.bo.PostBO;

@RestController
@RequestMapping("/marondalgram/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	// 포스트 작성
	@RequestMapping("/create")
	public Map<String, String> create(
			@RequestParam(value="content", required=false) String content
			,@RequestParam(value="file", required=false) MultipartFile file
			, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		// getAttribute는 리턴타입이 object이므로 Integer로 캐스팅한다.
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		// userId, content, file
		int count = postBO.addPost(userId, userName, content, file);
		
		Map<String, String> result = new HashMap<>();
					
			if(count == 1){
				result.put("result", "success");
			}else {
				result.put("result", "fail");
			}
			return result;
		
	}
	// 포스트 삭제
	@GetMapping("/delete")
	public Map<String, String> delete(
			@RequestParam("id") int id
			, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> result = new HashMap<>();
		
		int count = postBO.deletePost(id, userId);
		
		if(count == 1) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		return result;
	}
	
	
}
