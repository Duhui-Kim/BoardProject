package com.ssafy.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.LikeBoard;
import com.ssafy.board.model.dto.Review;
import com.ssafy.board.model.dto.SearchCondition;
import com.ssafy.board.model.dto.UploadFile;
import com.ssafy.board.model.dto.User;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.LikeBoardService;
import com.ssafy.board.model.service.ReviewService;
import com.ssafy.board.model.service.UploadFileService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private UploadFileService uploadFileService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private LikeBoardService likeBoardService;

	private User loginUser;

	// 초기화면
	@GetMapping("/")
	public String showIndex() {
		return "redirect:list";
	}

	// 리스트 보여주기
	@RequestMapping("/list*")
	public String list(HttpSession session, Model model, @RequestParam(required = false) @ModelAttribute String msg) {

		model.addAttribute("boards", boardService.getBoardList());
		model.addAttribute("bestBoards", boardService.getBestBoards());

		// session에 로그인 유저가 있으면 저장해둠
		if (session.getAttribute("loginUser") != null) {
			loginUser = (User) session.getAttribute("loginUser");
		} else {
			loginUser = null;
		}

		return "/board/list";
	}

	// 검색 기능
	@GetMapping("/search")
	public String search(Model model, SearchCondition condition) {

		model.addAttribute("boards", boardService.search(condition));
		model.addAttribute("bestBoards", boardService.getBestBoards());
		return "/board/list";
	}

	// 게시글 쓰는 창으로 가기
	@GetMapping("/writeform")
	public String writeform() {
		return "/board/writeform";
	}

	// 게시글 쓰기
	@PostMapping("/write")
	public String write(Board board, @RequestParam(required = false) List<MultipartFile> files) throws IOException {
		// 개행문자 적용
		board.setContent(board.getContent().replace("\r\n", "<br>"));

		// 게시글 등록
		boardService.writeBoard(board);

		// 파일 등록
		if (files != null) {
			for (MultipartFile f : files) {
				if (f.getSize() <= 0)
					break;
				uploadFileService.uploadFile(board, f);
			}
		}

		return "redirect:detail?id=" + board.getId();
	}

	// 게시글 상세보기
	@GetMapping("/detail")
	public String detail(HttpSession session, Model model, int id, String msg, boolean notModify) {
		Board board = boardService.readBoard(id);
		List<UploadFile> files = uploadFileService.readFile(id);
		List<Review> reviews = reviewService.getAllReviews(id);

		if (notModify) {
			session.removeAttribute("modifyReview");
		}

		model.addAttribute("msg", msg);
		model.addAttribute("board", board);
		model.addAttribute("files", files);
		model.addAttribute("reviews", reviews);

		// boardId + userId와 일치하는 likeBoardId를 가져와서 전달 (0인 경우 없는 것)
		model.addAttribute("likeId", likeBoardService.getLikeId(new LikeBoard(id, loginUser.getId())));

		return "/board/detail";
	}

	// 게시글 삭제 기능 (본인만 삭제 가능)
	@GetMapping("/delete")
	public String delete(Model model, int id, String writer) {

		// 관리자나 소유자가 아닐 경우
		if (!(loginUser.getAuthority().equals("관리자") || loginUser.getName().equals(writer))) {
			model.addAttribute("id", id);
			model.addAttribute("msg", "본인만 삭제가 가능합니다");
			return "redirect:detail";
		}

		boardService.removeBoard(id);
		return "redirect:list";
	}

	// 업데이트 폼으로 가기 (본인만 수정 가능)
	@GetMapping("/updateform")
	public String updateform(Model model, int id, String writer) {

		// 관리자나 소유자가 아닐 경우
		if (!(loginUser.getAuthority().equals("관리자") || loginUser.getName().equals(writer))) {
			model.addAttribute("id", id);
			model.addAttribute("msg", "본인만 수정이 가능합니다");
			return "redirect:detail";
		}

		Board board = boardService.readBoard(id);
		board.setContent(board.getContent().replace("<br>", "\r\n"));
		
		
		model.addAttribute("board", board);
		model.addAttribute("files", uploadFileService.readFile(id));
		model.addAttribute("reviews", reviewService.getAllReviews(id));

		return "/board/updateform";
	}

	// 업데이트 기능(파일도 업데이트 가능)
	@PostMapping("/update")
	public String update(Board board, @RequestParam(required = false) List<MultipartFile> files) throws IOException {

		// 개행문자 적용
		board.setContent(board.getContent().replace("\r\n", "<br>"));

		boardService.modifyBoard(board);

		// 추가된 파일이 있을 경우 업로드 시킨다
		if (files != null) {
			for (MultipartFile f : files) {
				if (f.getSize() <= 0)
					break;
				uploadFileService.uploadFile(board, f);
			}
		}

		return "redirect:detail?id=" + board.getId();
	}

	// 파일 다운로드 기능
	@GetMapping("/download")
	public String download(int fileId, Model model) {

		Map<String, Object> fileInfo = new HashMap<>();

		UploadFile uploadFile = uploadFileService.searchById(fileId);

		fileInfo.put("fileName", uploadFile.getFileName());
		fileInfo.put("orgFileName", uploadFile.getOrgFileName());

		model.addAttribute("downloadFile", fileInfo);

		return "fileDownLoadView";
	}

	// 파일 수정할 때 파일 삭제 가능
	@GetMapping("/deleteFile")
	public String deleteFile(Model model, int fileId, int boardId, String writer) {
		model.addAttribute("board", boardService.readBoard(boardId));
		model.addAttribute("id", boardId);
		model.addAttribute("writer", writer);

		uploadFileService.removeFile(fileId);

		return "redirect:updateform";
	}

	// 리뷰 등록하기
	@PostMapping("/registReview")
	public String registReview(Model model, int boardId, Review review) {
		review.setBoardId(boardId);
		review.setWriter(loginUser.getName());
		reviewService.registReview(review);
		model.addAttribute("id", boardId);

		return "redirect:detail";
	}

	// 리뷰 삭제하기
	@GetMapping("/deleteReview")
	public String deleteReview(Model model, int boardId, int reviewId) {
		model.addAttribute("id", boardId);

		if (!(loginUser.getAuthority().equals("관리자")
				|| reviewService.getReview(reviewId).getWriter().equals(loginUser.getName()))) {
			model.addAttribute("msg", "본인만 삭제가 가능합니다.");
			return "redirect:detail";

		} else {
			reviewService.removeReview(reviewId);
			return "redirect:detail";
		}
	}

	// 리뷰 수정 폼으로 이동
	@GetMapping("/modifyReview")
	public String modifyForm(HttpSession session, Model model, int boardId, int reviewId) {
		model.addAttribute("id", boardId);

		if (!(loginUser.getAuthority().equals("관리자")
				|| reviewService.getReview(reviewId).getWriter().equals(loginUser.getName()))) {
			model.addAttribute("msg", "본인만 수정이 가능합니다.");
			return "redirect:detail";

		} else {
			session.setAttribute("modifyReview", reviewService.getReview(reviewId));
			return "redirect:detail";
		}
	}

	// 리뷰 수정 후 게시글로 이동
	@PostMapping("/modifyReview")
	public String modifyReview(HttpSession session, Model model, int boardId, Review review) {
		session.removeAttribute("modifyReview");
		reviewService.modifyReview(review);

		model.addAttribute("id", boardId);
		return "redirect:detail";
	}

	// 마이페이지로 이동
	@GetMapping("/myPage")
	public String myPage(HttpSession session, Model model) {
		String userId = loginUser.getId();
		String name = loginUser.getName();

		model.addAttribute("boardCnt", boardService.registCnt(name));
		model.addAttribute("reviewCnt", reviewService.registCnt(name));
		model.addAttribute("likeBoard", boardService.getLikeBoard(userId));

		return "user/myPage";
	}

	// 마이페이지에서 좋아요 버튼 취소
	@GetMapping("/mpDislikeBoard")
	public String mpDislikeBoard(Model model, int boardId) {
		String userId = loginUser.getId();

		likeBoardService.dislikeBoard(new LikeBoard(boardId, userId));
		model.addAttribute("likeBoard", boardService.getLikeBoard(userId));

		return "redirect:myPage";
	}

	// 게시글 상세화면에서 좋아요 버튼 클릭
	@GetMapping("/likeBoard")
	public String likeBoard(Model model, int boardId) {
		String userId = loginUser.getId();

		likeBoardService.likeBoard(new LikeBoard(boardId, userId));

		model.addAttribute("id", boardId);
		model.addAttribute("likeId", likeBoardService.getLikeId(new LikeBoard(boardId, userId)));

		return "redirect:detail";
	}

	// 게시글 상세화면에서 좋아요 버튼 취소
	@GetMapping("/dislikeBoard")
	public String dislikeBoard(Model model, int boardId) {
		likeBoardService.dislikeBoard(new LikeBoard(boardId, loginUser.getId()));

		model.addAttribute("id", boardId);
		model.addAttribute("likeId", 0);

		return "redirect:detail";
	}

	// 404 에러 처리
	@GetMapping("/error/404")
	public String error404(Model model) {
		return "error/404";
	}
}
