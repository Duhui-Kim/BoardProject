<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<%@ include file="../common/bootstrap.jsp" %>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp" %>
		
		
		<h2 class="list-bestboard-title"><i class="fa-brands fa-hotjar" style="color: #ec3c3c;"></i>&nbsp;인기글</h2>
		<div class="list-bestboard row row-cols-1 row-cols-md-3 g-3">
			<div class="list-bestboard-carddiv col">
				<div class="card list-bestboard-card">
					<div class="card-body">
					  <h5 class="card-title"><i class="fa-brands fa-hotjar" style="color: #ec3c3c;"></i>&nbsp;${bestBoards.get(0).title }</h5>
					  <h6 class="card-subtitle mb-2 text-muted">${bestBoards.get(0).writer }</h6>
					  <p class="card-text list-bestboard-text">${bestBoards.get(0).content }</p><br>
					  <a href="detail?id=${bestBoards.get(0).id}" class="btn btn-color-2 list-bestboard-card-detailbtn">글 보러가기</a>
					</div>
				</div>					
			</div>
			<div class="list-bestboard-carddiv col">
				<div class="card list-bestboard-card">
					<div class="card-body">
					  <h5 class="card-title"><i class="fa-brands fa-hotjar" style="color: #ec3c3c;"></i>&nbsp;${bestBoards.get(1).title }</h5>
					  <h6 class="card-subtitle mb-2 text-muted">${bestBoards.get(1).writer }</h6>
					  <p class="card-text list-bestboard-text">${bestBoards.get(1).content }</p><br>
					  <a href="detail?id=${bestBoards.get(1).id}" class="btn btn-color-2 list-bestboard-card-detailbtn">글 보러가기</a>
					</div>
				</div>				
			</div>
			<div class="list-bestboard-carddiv col">
				<div class="card list-bestboard-card">	
					<div class="card-body">
					  <h5 class="card-title"><i class="fa-brands fa-hotjar" style="color: #ec3c3c;"></i>&nbsp;${bestBoards.get(2).title }</h5>
					  <h6 class="card-subtitle mb-2 text-muted">${bestBoards.get(2).writer }</h6>
					  <p class="card-text list-bestboard-text">${bestBoards.get(2).content }</p><br>
					  <a href="detail?id=${bestBoards.get(2).id}" class="btn btn-color-2 list-bestboard-card-detailbtn">글 보러가기</a>
					</div>
				</div>
			</div>
		</div>

		<!-- 좋아요 상위 3개 보드가 담겨있는 리스트 // 참고 : bestBoards.get(0) 하면 첫번째 board임. board 유무에 따라 null이면 표시할 것도 필요할듯 -->
		<br>
    	<hr>
		<div class="list-boardlist-header">
			<h2>전체 게시글 목록</h2>
			<a class="btn btn-color-3 list-registbtn" href="writeform">글등록</a>
		</div>
		<hr>		
		<div>
			<table class="table text-center">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓰니</th>
					<th>조회수</th>
					<th>좋아요 개수</th>
					<th>등록날짜</th>
				</tr>
				<c:forEach items="${boards}" var="board">
					<tr>
						<td>${board.id}</td>
						<td><a href="detail?id=${board.id}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.viewCnt}</td>
						<td>${board.likeCnt }</td>
						<td>${board.regDate}</td>
					</tr>
				</c:forEach>
			</table>
			
			<form action="search" class="row list-search-area">
				<div class="col-2">
					<label>검색 기준</label>
					<select name="key" class="form-select">
						<option value="none">없음</option>
						<option value="writer">글쓰니</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
				</div>
				<div class="col-5">
					<label>검색 내용</label>
					<input type="text" name="word" class="form-control">
				</div>
				<div class="col-2">
					<label>정렬 기준</label>
					<select name="orderBy" class="form-select">
						<option value="none">없음</option>
						<option value="writer">글쓰니</option>
						<option value="title">제목</option>
						<option value="view_cnt">조회수</option>
					</select>
				</div>
				<div class="col-2">
					<label>정렬 방향</label>
					<select name="orderByDir" class="form-select">
						<option value="asc">오름차순</option>
						<option value="desc">내림차순</option>
					</select>
				</div>
				<div class="col-1">	
					<label>&nbsp;</label><br>
					<input type="submit" value="검색" class="btn btn-color-3">
				</div>
			</form>
			
			
		</div>
		
	</div>
	<br><br>
</body>
</html>