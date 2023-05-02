package com.ssafy.board.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.board.model.dto.User;

@Component
public class ManagerInterceptor implements HandlerInterceptor {
	
	// 관리자 권한이 아닌 상태에서 특정 페이지 접근 시 접근 제한
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		if(loginUser == null || !loginUser.getAuthority().equals("관리자")) {
			request.setAttribute("msg", "접근할 수 없는 페이지입니다.");			
			
			RequestDispatcher disp = request.getRequestDispatcher("/list");
			disp.forward(request, response);
			return false;
		}
		
		return true;
	}
}
