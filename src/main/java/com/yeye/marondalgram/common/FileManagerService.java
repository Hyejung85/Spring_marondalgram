package com.yeye.marondalgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String FILE_UPLOAD_PATH = "C:\\Users\\82109\\Desktop\\spring\\spring_test\\upload\\marondalgram\\images/";
	
	// 파일 업로드
	public String saveFile(int userId, MultipartFile file) {
		
		// 파일 이름 겹침 방지를 위해 사용자별로 디렉토리 만들어서 저장한다.
		// 사용자 이름 _ 1970년 1월 1일 이후료 몇초가 지났는지 /
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		// 완전한 파일경로
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		// 파일을 저장할 디렉토리 생성
		File directory = new File(filePath);
		
			// 예외처리
			if(directory.mkdir() == false) {
				logger.error("[FileManagerService] 디렉토리 생성 실패");
				return null;
			}
		// 파일 저장(byte 단위)
		byte[] bytes;
		try {
			bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			logger.error("[FileManagerService] 파일 생성 실패");
			e.printStackTrace();
			return null;
		}
		
		// 파일이 접근 가능한 path 리턴
		// src="images/yeye_11111111/test.png"
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	


}
