package com.ssafy.board.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	// 로그인 되어 있지 않을 시 접근 제한
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			request.setAttribute("msg", "로그인이 필요합니다.");			
			
			RequestDispatcher disp = request.getRequestDispatcher("/list");
			disp.forward(request, response);
			return false;
		} else {
			return true;
		}
	} 
}
