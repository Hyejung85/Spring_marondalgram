package com.yeye.marondalgram.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yeye.marondalgram.post.comment.model.Comment;
import com.yeye.marondalgram.post.model.PostWithComment;

@Repository
public interface CommentDAO {

	// 코멘트 작성
		public int insertComment(@Param("postId") int postId
				, @Param("userId") int userId
				, @Param("userName") String userName
				, @Param("content") String content);
		
	// 코멘트 리스트
		public List<Comment> selectCommentByPostId(@Param("postId") int postId);
}
