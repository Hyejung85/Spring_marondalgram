package com.yeye.marondalgram.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeye.marondalgram.post.like.dao.LikeDAO;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;	
	
	// 좋아요
	public int like(int postId, int userId) {
		return likeDAO.insertLike(postId, userId);
	}
	
	// 좋아요 취소
	public int dislikeById(int Id) {
		return likeDAO.deleteLikeById(Id);
	}
}
