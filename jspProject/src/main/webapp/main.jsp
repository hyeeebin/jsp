<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) session.getAttribute("login.id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>메인 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
	
	<!-- content -->
	<div id="section">
	<a href="boardList">일반게시판</a>
		<div class="pageContent">
			<figure>
        		<img src="image/notice.jpg">
	      </figure>
			<header>
	        	<h3>공지사항</h3>
	        </header>
		</div>
		<div class="pageContent">
			<figure>
        		<img src="image/common.jpg">
	      </figure>
			<header>
	        	<h3>일반게시판</h3>
	        </header>
		</div>
		<div class="pageContent">
			<figure>
        		<img src="image/Q&A.jpg">
	      </figure>
			<header>
	        	<h3>Q&A</h3>
	        </header>
		</div>
		<div class="pageContent">
			<figure>
        		<img src="image/chat.jpg">
	      </figure>
			<header>
	        	<h3>채팅</h3>
	        </header>
		</div>
	</div>
</body>
</html>