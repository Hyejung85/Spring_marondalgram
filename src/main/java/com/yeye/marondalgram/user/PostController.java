package com.yeye.marondalgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	
	// post 목록
		@GetMapping("/post/list_view")
		public String postList() {
			return "marondalgram/post/list";
		}
		
		// POST 올리기
		@GetMapping("/post/create_view")
		public String postCreat() {
			return "marondalgram/post/create";
		}
		
		// POST  상세 및 수정
		@GetMapping("/post/detail_view")
		public String postDetail() {
			return "marondalgram/post/detail";
		}
		

}
