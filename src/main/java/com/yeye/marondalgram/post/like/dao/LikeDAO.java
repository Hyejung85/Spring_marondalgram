package com.yeye.marondalgram.post.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	// 좋아요
	public int insertLike(
			@Param("postId") int postId
			, @Param("userId") int userId);
	
	// 좋아요 취소
	public int deleteLikeById(
			@Param("Id") int id);
}
