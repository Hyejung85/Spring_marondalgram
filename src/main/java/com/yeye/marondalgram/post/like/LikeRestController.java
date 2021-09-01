package com.yeye.marondalgram.post.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yeye.marondalgram.post.like.bo.LikeBO;

@RestController
@RequestMapping("/marondalgram/post")
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// 좋아요 입력
	@GetMapping("/like")
	public Map<String, String> like(
			@RequestParam("postId") int postId
			, HttpServletRequest request){
		
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> likeList = new HashMap<>();
		int count = likeBO.like(postId, userId);
		
		if(count == 1) {
			likeList.put("likeList", "success");
		}else {
			likeList.put("likeList","fail");
		}
		
		return likeList;
		
	}
	
	// 좋아요 취소
	@GetMapping("/dislike")
	public Map<String, String> dislike(
			@RequestParam("id") int id
			){
		
		Map<String, String> dislikeList = new HashMap<>();
		int count = likeBO.dislikeById(id);
		
		if(count == 1) {
			dislikeList.put("result", "success");
		}else {
			dislikeList.put("result", "fail");
		}
		return dislikeList;
	}
}
