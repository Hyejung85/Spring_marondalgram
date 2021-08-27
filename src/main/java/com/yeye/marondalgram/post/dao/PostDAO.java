package com.yeye.marondalgram.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yeye.marondalgram.post.model.Post;

@Repository
public interface PostDAO {

	// 포스트 작성
	public int insertPost(
			@Param("userId") int userId
			, @Param("userName") String userName
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	// 포스팅 리스트
	public List<Post> selectPostList();

}
