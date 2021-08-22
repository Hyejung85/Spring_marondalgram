package com.yeye.marondalgram.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yeye.marondalgram.user.model.User;

@Repository
public interface UserDAO {
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);
	
	// 아이디 중복확인
	public int selectCountById(@Param("loginId") String loginId);
	
	// 회원가입
	public User selectUserByIdPassword(
			@Param("loginId") String loginId
			, @Param("password") String password);
}
