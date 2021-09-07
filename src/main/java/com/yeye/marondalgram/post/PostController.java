package com.yeye.marondalgram.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yeye.marondalgram.post.bo.PostBO;
import com.yeye.marondalgram.post.model.PostWithComment;

@Controller
@RequestMapping("/marondalgram/post")
public class PostController {

	@Autowired
	private PostBO postBO;
	
	// 포스팅 리스트 + 코멘트 리스트 + 라이크했는지
	@RequestMapping("/timeline")
	public String postList(Model model
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
				
		Integer userId = 0;
		if((Integer)session.getAttribute("userId") != null) {
			userId = (Integer)session.getAttribute("userId");
			List<PostWithComment> postList = postBO.getPostList(userId);
			model.addAttribute("postList", postList);
		}else {
			// null이지만, 리스트를 볼 수있어야 한다.
			List<PostWithComment> postList = postBO.getPostList(userId);
			model.addAttribute("postList", postList);
		}
		
		return "marondalgram/post/timeline";
	}
	

}
