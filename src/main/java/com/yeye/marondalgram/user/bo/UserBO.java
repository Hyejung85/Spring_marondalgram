package com.yeye.marondalgram.user.bo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeye.marondalgram.common.EncryptUtils;
import com.yeye.marondalgram.user.dao.UserDAO;
import com.yeye.marondalgram.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	// 회원가입 & 비밀번호 암호화
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int signUp(String loginId, String password, String name, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		if(encryptPassword.equals("")) {
			logger.error("[UserBO signUp] 암호화 실패!!!!!");
			return 0;
		}
		
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	// 아이디 중복확인
	public boolean isDuplicateId (String loginId) {
		if(userDAO.selectCountById(loginId) == 0 ) {
			return false;
		} else {
			return true;
		}
	}
	
	// 로그인
	public User signIn(String loginId, String password) {
		// 비밀번호를 암호화하고 DAO로 전달한다.
		String encryptPassword = EncryptUtils.md5(password);
		return userDAO.selectUserByIdPassword(loginId, encryptPassword);
	}
	
}
