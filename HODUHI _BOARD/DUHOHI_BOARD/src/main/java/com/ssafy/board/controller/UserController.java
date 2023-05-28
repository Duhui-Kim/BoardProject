package com.ssafy.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.dto.User;
import com.ssafy.board.model.service.UserService;

@Controller
public class UserController {
	
	// password 암호화
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// 암호를 강화하기 위한 salt
	private final String SALT = "DUHUI_BOARD";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceLoader resLoader;
	
	// 유저 목록 보여주기
	@GetMapping("users")
	public String userList(Model model) {
		model.addAttribute("userList", userService.selectAll());		
		return "/user/userList";
	}
	
	// 회원탈퇴 시키기
	@GetMapping("forced")
	public String forcedWithdrawal(String id) {
		userService.delete(id);
		return "redirect:users";
	}
	
	// 로그인 폼 이동
	@GetMapping("login")
	public String loginForm() {
		return "/user/loginform";
	}
	
	// 로그인 (관리자계정 : id=peach, pw=1234)
	@PostMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response, User user) throws IOException, ServletException {
		// 이미 로그인이 되어있는 경우에는 아래의 과정을 거치지 않는다.
		if(request.getSession().getAttribute("loginUser") != null) 
			return "redirect:list";
		
		User loginUser = userService.selectById(user.getId());
		HttpSession session = request.getSession();
		
		// DB에 아이디가 일치하는 유저가 있을 경우
		if(loginUser != null) {
			// ID, Password 일치하면 로그인
			if(passwordEncoder.matches(user.getPassword() + SALT, loginUser.getPassword())) {
				session.setAttribute("loginUser", loginUser);
				request.setAttribute("msg", loginUser.getName() + "님 환영합니다.");
				
			} else { // password 틀림
				request.setAttribute("msg", "비밀번호를 확인해주세요");
			}
		 // 일치하는 아이디가 없을 경우
		} else request.setAttribute("msg", "아이디를 확인해주세요");
		
		RequestDispatcher disp = request.getRequestDispatcher("/list");
		disp.forward(request, response);
		return null;
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:list";
	}
	
	// 회원가입 폼으로 이동
	@GetMapping("signup")
	public String signupform(@RequestParam(required = false) @ModelAttribute String msg) {
		return "/user/signupform";
	}
	
	// 회원가입
	@PostMapping("signup")
	public String signup(HttpServletRequest request, HttpServletResponse response, User user, @RequestParam(required = false) MultipartFile uploadFile) throws ServletException, IOException {
		// 공백 거르기
		if(user.getId() == null || user.getId().equals("")) {
			request.setAttribute("msg", "아이디를 입력해주세요");
			
			RequestDispatcher disp = request.getRequestDispatcher("/list");
			disp.forward(request, response);
		}
		// 중복아이디 거르기
		else if(userService.selectById(user.getId()) != null) {
			request.setAttribute("msg", "중복되는 아이디가 있습니다.");
			
			RequestDispatcher disp = request.getRequestDispatcher("/list");
			disp.forward(request, response);
		}
		
		// 비밀번호 암호화하기
		String encodingPass = passwordEncoder.encode(user.getPassword() + SALT);
		user.setPassword(encodingPass);

		// 이미지 등록하기
		if(uploadFile.getSize() > 0) {
			Resource res = resLoader.getResource("/resources/image");
			
			if(!res.getFile().exists()) 
				res.getFile().mkdir();
			
			// UUID에 확장자만 추가하기
			int idx = uploadFile.getOriginalFilename().lastIndexOf('.');
			user.setImg(UUID.randomUUID() + "_" + uploadFile.getOriginalFilename().substring(idx));			
			user.setOrgImg(uploadFile.getOriginalFilename());
			
			uploadFile.transferTo(new File(res.getFile(), user.getImg()));
		}
		
		// DB에 등록
		userService.insert(user);

		return "redirect:list";
	}
	
	// 회원 탈퇴
	@GetMapping("withdraw")
	public String withdraw(HttpSession session, String id) {
		session.removeAttribute("loginUser");
		userService.delete(id);
		return "redirect:list";
	}
	
}
