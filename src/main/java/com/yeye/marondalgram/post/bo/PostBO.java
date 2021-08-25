package com.yeye.marondalgram.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yeye.marondalgram.common.FileManagerService;
import com.yeye.marondalgram.post.dao.PostDAO;
import com.yeye.marondalgram.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	// 포스트 작성
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		String filePath = null;
		// 파일 없이도 인서트 되도록 설정
		if(file != null) {
			
			FileManagerService fileManagerService = new FileManagerService();
			
			filePath = fileManagerService.saveFile(userId, file);
			
			if(filePath == null) {
				return -1;
			}
		}
		
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	// 리스트
	public List<Post> getPostList() {
		return postDAO.selectPostList();
		
	}
}
