package com.yeye.marondalgram.post.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yeye.marondalgram.post.comment.bo.CommentBO;

@RestController
@RequestMapping("/marondalgram/post")
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;
	// 코멘트 작성
		@GetMapping("/comment/create")
		public Map<String, String> addComment(
				@RequestParam("postId") int postId
				, @RequestParam("content") String content
				, HttpServletRequest request){
			
			HttpSession session = request.getSession();
			
			String userName = (String)session.getAttribute("userName");
			int userId = (Integer)session.getAttribute("userId");
			
			int count = commentBO.addComment(postId, userId, userName, content);
			
			Map<String, String> result = new HashMap<>();
			
			if (count == 1) {
				result.put("result", "success");
			}else {
				result.put("result", "fail");
			}
			return result;
			
		}
		
	// 코멘트만 삭제 (포스트와 함께 전체 삭제는 PostBO에 작성)
		@GetMapping("/comment/delete")
		public Map<String, String> deleteComment(
				@RequestParam("id") int id
				, HttpServletRequest request){
			
			HttpSession session = request.getSession();
			String userName = (String)session.getAttribute("userName");
			int userId = (Integer)session.getAttribute("userId");
			
			int count = commentBO.deleteCommentById(id);
			
			Map<String, String> result = new HashMap<>();
			
			if (count == 1) {
				result.put("result", "success");
			}else {
				result.put("result", "fail");
			}
			return result;
			
		}
}
