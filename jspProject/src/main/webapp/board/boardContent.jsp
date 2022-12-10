<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="board.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String id = (String) session.getAttribute("login.id");
/* if (null == id) {
	response.sendRedirect("login.jsp");
} */

Board b = (Board)request.getAttribute("board");
System.out.println(b);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 페이지</title>
<link href="css/style.css" rel="stylesheet" />
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/translations/ko.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="page1">
	<h1>게시물 상세보기</h1>
	<%
		System.out.println("게시물 상세페이지 세션아이디 : " + id);
		System.out.println("게시물 작성자 아이디: "+ b.getId());
	%>
		
		<form method="post" name="updateBoard" action="updateBoard?bno=${board.bno}">
	    	<table class="board_detail">
				<colgroup>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">글 번호</th>
						<td th:text>${board.bno}</td>
						<th scope="row">게시판</th>
						<td th:text>${board.btype}</td>
					</tr>
					<tr>
						<th scope="row">작성자</th>
						<td th:text>${board.id}</td>
						<th scope="row">작성일</th>
						<td th:text=>${board.regDate}</td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3">
							<input type="text" id="title" name="title" 
								value="${board.title}"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="view_text">
							<textarea title="내용" id="content" name="content" 
								th:text>${board.content}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		<%if ( b.getId().equals(id)) { %>
		<input type="submit" value="수정하기">
		<%} %> 
	</form>
	<%if ( b.getId().equals(id)) { %>
		<form method="post" name="deleteBoard" action="#">
			<input type="button" value="삭제하기" onclick="removeCheck()">
		</form>
	<%} %> 
	<form method="post">
		<input type="button" value="뒤로" onClick="history.go(-1)">
	</form>
	</div>
</body>
<script>

function removeCheck() {
    if (!confirm("삭제시 복구할 수 없습니다. 진행하시겠습니까?")) {
        alert("취소를 누르셨습니다. 게시물 상세보기 페이지로 이동합니다.");
        /* location.replace("board/board.Content.jsp"); */
    } else {
        location.replace("removeBoardContent?bno=${board.bno}");
    }
}
</script>
<script>
        ClassicEditor
            .create( document.querySelector( '#content' ), {language : "ko"} )
            .catch( error => {
                console.error( error );
        } );
</script>
</html>