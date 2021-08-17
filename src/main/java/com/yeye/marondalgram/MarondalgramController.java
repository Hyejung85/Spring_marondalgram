package com.yeye.marondalgram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/marondalgram")
public class MarondalgramController {
	
	// post 목록
	@GetMapping("/post/list_view")
	public String postList() {
		return "marondalgram/list";
	}
}
