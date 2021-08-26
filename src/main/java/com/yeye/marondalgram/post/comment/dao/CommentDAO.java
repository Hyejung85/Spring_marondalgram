package com.yeye.marondalgram.post.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO {

	// 코멘트 작성
		public int insertComment(@Param("postId") int postId
				, @Param("userId") int userId
				, @Param("userName") String userName
				, @Param("content") String content);
}
