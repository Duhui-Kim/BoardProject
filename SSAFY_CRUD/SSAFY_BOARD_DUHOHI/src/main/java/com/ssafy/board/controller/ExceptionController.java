package com.ssafy.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public String handleError(HttpSession session, Exception e, Model model) {

		model.addAttribute("message", e.getStackTrace());
						
		if(session.getAttribute("modifyReview") != null) {
			session.removeAttribute("modifyReview");
		}

		return "error/commonError";
	}
}
