package com.yeye.marondalgram.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeye.marondalgram.post.bo.PostBO;
import com.yeye.marondalgram.post.model.PostWithComment;

@Controller
@RequestMapping("/marondalgram/post")
public class PostController {

	@Autowired
	private PostBO postBO;
	
	//@GetMapping("/timeline")
	//public String createView() {
	//	return "marondalgram/post/timeline";
	//}
	
	// 포스팅 리스트 + 코멘트 리스트 + 라이크했는지
	@RequestMapping("/timeline")
	public String postList(Model model
			, HttpServletRequest request) {
		
		// 포스트,코멘트 리스트 
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		List<PostWithComment> postList = postBO.getPostList(userId);
		
				
		model.addAttribute("postList", postList);

		
		return "marondalgram/post/timeline";
	}
	

}
