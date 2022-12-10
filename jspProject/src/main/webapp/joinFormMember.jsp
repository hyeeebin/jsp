<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
<div id="page">
	<h1>회원가입</h1>
	<form action="join" name="join" method="post" autocomplete="off">
		<input type="text" id="id" name="id" placeholder="아이디" autocomplete="off"><br>
<!-- 		<input type="button" value="아이디 중복 확인"><br> -->
		<input type="password" id="pwd" name="pwd" placeholder="비밀번호" autocomplete="off"> <br>
		<input type="password" id="pwd" name="pwd2" placeholder="비밀번호 확인"><br>
		<input type="text" id="name" name="name" placeholder="이름"><br>
		<input type="tel" id="phone" name="phone" placeholder="전화번호"><br>
		<input type="email" id="email" name="email" placeholder="이메일"><br>
		<input type="submit" value="회원가입"><br>
		<input type="reset" value="다시 입력">
	</form>
</div>
</body>
</html>