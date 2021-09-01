package com.yeye.marondalgram.post.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yeye.marondalgram.post.like.model.Like;

@Repository
public interface LikeDAO {

	// 좋아요
	public int insertLike(
			@Param("postId") int postId
			, @Param("userId") int userId);
	
	// 좋아요 했는지 확인
	public int selectCountLike(
			@Param("postId") int postId
			, @Param("userId") int userId);
 	
	// 좋아요 취소
	public int deleteLikeById(
			@Param("id") int id);
}
