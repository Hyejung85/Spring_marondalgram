package com.yeye.marondalgram.post.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeye.marondalgram.post.comment.dao.CommentDAO;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	// 코멘트 작성
		public int addComment(int postId, int userId, String userName, String content) {
			return commentDAO.insertComment(postId, userId, userName, content);
		}
}
