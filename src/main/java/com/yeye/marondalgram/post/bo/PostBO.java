package com.yeye.marondalgram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yeye.marondalgram.common.FileManagerService;
import com.yeye.marondalgram.post.comment.bo.CommentBO;
import com.yeye.marondalgram.post.comment.model.Comment;
import com.yeye.marondalgram.post.dao.PostDAO;
import com.yeye.marondalgram.post.model.Post;
import com.yeye.marondalgram.post.model.PostWithComment;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	// 포스트 작성
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
			
			FileManagerService fileManagerService = new FileManagerService();
			
			String filePath = fileManagerService.saveFile(userId, file);
			
			if(filePath == null) {
				return -1;
			}
		
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	// 포스팅 리스트
	public List<PostWithComment> getPostList() {
		List<Post> postList = postDAO.selectPostList();
		
		List<PostWithComment> postWithCommentList = new ArrayList<>();
		for(Post post : postList) {
			List<Comment> commentList = commentBO.getCommentList(post.getId());
			
			PostWithComment postWithComment = new PostWithComment();
			postWithComment.setPost(post);
			postWithComment.setCommentList(commentList);
			
			postWithCommentList.add(postWithComment);
		}
			
		return postWithCommentList;
		
	}
	
}
