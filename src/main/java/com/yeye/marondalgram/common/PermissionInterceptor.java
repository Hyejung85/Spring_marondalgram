package com.yeye.marondalgram.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
		HttpSession session = request.getSession();
		
		// 로그인이 안된 상태, 현재 path 알아오기
		String uri = request.getRequestURI();
		
		// 로그인이 되어있지 않다면
		if(session.getAttribute("userId") == null){
			if(uri.startsWith("/marondalgram/post")) {
				response.sendRedirect("/marondalgram/user/signin_view"); // 로그인 페이지로 이동한다.
				return false;
			}
		} else {
			if(uri.startsWith("/marondalgram/user")) { // user관련 페이지 접근시
				response.sendRedirect("/marondalgram/post/timeline"); // 포스트 페이지로 이동시킨다.
				return false;
			}
		}
		
		return true;
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView modelAndView ) {
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Object handler, Exception ex) {
		
	}
	
}
