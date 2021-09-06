package com.yeye.marondalgram.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeye.marondalgram.post.comment.dao.CommentDAO;
import com.yeye.marondalgram.post.comment.model.Comment;
import com.yeye.marondalgram.post.model.PostWithComment;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	// 코멘트 작성
		public int addComment(int postId, int userId, String userName, String content) {
			return commentDAO.insertComment(postId, userId, userName, content);
		}
		
	// 코멘트 리스트
		public List<Comment> getCommentList(int postId){
			return commentDAO.selectCommentByPostId(postId);
		}
		
	
	
		public int deleteComment(int postId) {
			return commentDAO.deleteComment(postId);
		}
}
