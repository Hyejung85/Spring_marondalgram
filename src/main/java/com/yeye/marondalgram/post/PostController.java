package com.yeye.marondalgram.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeye.marondalgram.post.bo.PostBO;
import com.yeye.marondalgram.post.model.Post;

@Controller
@RequestMapping("/marondalgram/post")
public class PostController {

	@Autowired
	private PostBO postBO;
	
	//@GetMapping("/timeline")
	//public String createView() {
	//	return "marondalgram/post/createView";
	//}
	
	// 리스트
	@GetMapping("/timeline")
	public String postList(Model model) {
		
		List<Post> postList = postBO.getPostList();
		model.addAttribute("postList", postList);
		return "marondalgram/post/createView";
	}

}
