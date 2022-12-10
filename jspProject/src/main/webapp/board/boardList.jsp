<%@page import="board.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String id = (String) session.getAttribute("login.id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판 목록 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
		<div id="h1">
			<h1>일반 게시판</h1>
		</div>
		<div id="searchBoard">
			<select>
				<option>작성자</option>
				<option>제목</option>
			</select>
			<input type="text" id="search" name="search" placeholder="검색할 내용을 입력하세요.">
			<input type="submit" value="검색" id="searchBtn">
		</div>
		<div id="table">
		<table border='1'>
			<thead>
				<tr>
					<th>NO</th>
					<th>BNO</th>
					<th>게시판 분류</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일자</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" value="1" />
				<c:set var="i">1</c:set>
				<c:forEach var="board" items="${list}" varStatus="listBoardStatus">
					<tr class="${listBoardStatus.count % 2 == 0 ? 'trEven' : 'trOdd'}">
						<td>${listBoardStatus.count}</td>
						<td>${board.bno}</td>
						<td>${board.btype}</td>
						<!-- 글제목 클릭시 게시물 상세보기페이지로 이동하는데 글번호를 get 방식으로 함께 넘겨주기-->
						<td><a href="boardContent?bno=${board.bno}">${board.title}</a></td>
						<td>${board.id}</td>
						<td>${board.regDate}</td>
						<td>${board.readCount}</td>
						<c:set var="i">${i+1}</c:set>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<form action="board/newBoard.jsp" method="post" name="newBoard">
			<input type="submit" value="글쓰기" id="write">
			<%-- 	<%
	if (id != null) {
	%>
		<input type="submit" value="글쓰기">
	<%
	} else {
	response.sendRedirect("login.jsp");
	}
	%> --%>
		</form>
		<form method="post" action="main.jsp">
			<input type="submit" value="메인으로 돌아가기">
		</form>

</body>
</html>