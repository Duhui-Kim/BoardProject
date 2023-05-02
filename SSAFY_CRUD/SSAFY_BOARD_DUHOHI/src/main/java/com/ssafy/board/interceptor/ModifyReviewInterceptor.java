package com.ssafy.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ModifyReviewInterceptor implements HandlerInterceptor {
	
	// 리뷰 수정 중 다른 페이지로 이동 시 수정 취소
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("modifyReview") != null) 
			session.removeAttribute("modifyReview");
		
		return true;
	}
}
