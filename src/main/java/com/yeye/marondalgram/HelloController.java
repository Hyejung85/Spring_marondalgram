package com.yeye.marondalgram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@RequestMapping("/helloworld")
	@ResponseBody
	public String test() {
		return "hello world";
	}
}
