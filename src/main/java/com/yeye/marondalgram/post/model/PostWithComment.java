package com.yeye.marondalgram.post.model;

import java.util.ArrayList;
import java.util.List;

import com.yeye.marondalgram.post.comment.model.Comment;

public class PostWithComment {
	private Post post;
	private List<Comment> commentList;
	private boolean isLike;
	private int likeCount;
	
	
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}

