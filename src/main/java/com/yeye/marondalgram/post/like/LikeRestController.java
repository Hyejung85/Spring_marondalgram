package com.yeye.marondalgram.post.like;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yeye.marondalgram.post.like.bo.LikeBO;
import com.yeye.marondalgram.post.model.PostWithComment;

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
		
		// 좋아요
		Map<String, String> likeList = new HashMap<>();
		int like = likeBO.like(postId, userId);
		int totalLike = likeBO.totalCount(postId);
		
		if(like == 1) {
			likeList.put("likeList", "success");
			likeList.put("totalLike", "totalLike");
		}else {
			likeList.put("likeList","fail");
			}
		
		return likeList;
		
	}
	
	// 좋아요 취소
	@GetMapping("/dislike")
	public Map<String, String> dislike(@RequestParam("postId") int postId
			, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 싫어요
		Map<String, String> dislikeList = new HashMap<>();
		int dislike = likeBO.dislike(postId,userId);
		int totalLike = likeBO.totalCount(postId);
		
		if(dislike == 1) {
			dislikeList.put("dislikeList", "success");
			dislikeList.put("totalLike", "totalLike");
		}else {
			dislikeList.put("dislikeList", "fail");
		}
		return dislikeList;
	}
	
	
}
