package com.yeye.marondalgram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yeye.marondalgram.common.FileManagerService;
import com.yeye.marondalgram.post.comment.bo.CommentBO;
import com.yeye.marondalgram.post.comment.model.Comment;
import com.yeye.marondalgram.post.dao.PostDAO;
import com.yeye.marondalgram.post.like.bo.LikeBO;
import com.yeye.marondalgram.post.model.Post;
import com.yeye.marondalgram.post.model.PostWithComment;

@Service
public class PostBO {
	
	// 포스트
	@Autowired
	private PostDAO postDAO;
	
	// 코멘트
	@Autowired
	private CommentBO commentBO;
	
	// 라이크
	@Autowired
	private LikeBO likeBO;
	
	// 로거 사용
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 포스트 작성
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
			
			FileManagerService fileManagerService = new FileManagerService();
			
			String filePath = fileManagerService.saveFile(userId, file);
			
			if(filePath == null) {
				return -1;
			}
		
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	// 포스팅 리스트 (+코멘트 +라이크)
	public List<PostWithComment> getPostList(int userId) {
		List<Post> postList = postDAO.selectPostList();
		
		List<PostWithComment> postWithCommentList = new ArrayList<>();
		
		for(Post post : postList) {
			List<Comment> commentList = commentBO.getCommentList(post.getId());
			
			boolean isLike = likeBO.existLike(post.getId(), userId);
			
			
			PostWithComment postWithComment = new PostWithComment();
			// 좋아요 갯수
			int likCount = likeBO.countLike(post.getId());
			
			postWithComment.setPost(post);
			// 코멘트 리스트
			postWithComment.setCommentList(commentList);
			// 좋아요가 된 상태인지
			postWithComment.setLike(isLike);
			// 좋아요 갯수
			postWithComment.setLikeCount(likCount);
			
			
			postWithCommentList.add(postWithComment);
		}
			
		return postWithCommentList;
		
	}
	// 포스팅 삭제
	public boolean deletePost(int postId, int userId) {
		
		// 파일 삭제
		Post post = postDAO.selectPost(postId);
		// 포스트 삭제
		int count = postDAO.deletePost(postId, userId);
		
		// 코멘트부터 삭제 실패하는지 확인
		if(count != 1) {
			return false;
		}
		// 파일 삭제
		FileManagerService fileManagerSerive = new FileManagerService();
		fileManagerSerive.removeFile(post.getImagePath());
		
		// 라이크, 코멘트 삭제
		int likeCount = likeBO.deleteLike(postId);
		int commentCount = commentBO.deleteComment(postId);
		
		
		return true;
	}
	
}
