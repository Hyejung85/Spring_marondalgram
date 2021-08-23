package com.yeye.marondalgram.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marondalgram/post")
public class PostController {

	@GetMapping("/timeline")
	public String createView() {
		return "marondalgram/post/createView";
	}
}
