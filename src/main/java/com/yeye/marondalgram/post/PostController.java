package com.yeye.marondalgram.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 포스팅 리스트 + 코멘트 리스트
	@RequestMapping("/timeline")
	public String postList(Model model) {
		
		// 포스트 리스트 
		List<PostWithComment> postList = postBO.getPostList();
		
		model.addAttribute("postList", postList);

		
		return "marondalgram/post/timeline";
	}
	

}
