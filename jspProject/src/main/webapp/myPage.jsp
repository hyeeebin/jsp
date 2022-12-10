<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세보기 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="./main/header.jsp"></jsp:include>
<div id="page1">
	<h1>MYPAGE</h1>
	<br>
	<form action="update" name="myPage" method="post">
		<input type="text" id="id" name="id" placeholder="아이디" value="${member.id}"  readonly="readonly"><br>
		<input type="password" id="pwd" name="pwd" placeholder="비밀번호"><br>
		<input type="password" id="pwd" name="pwd2" placeholder="비밀번호 확인"><br>
		<input type="text" id="name" name="name" placeholder="이름" value="${member.name}" readonly="readonly"><br>
		<input type="tel" id="phone" name="phone" placeholder="전화번호" value="${member.phone}"><br>
		<input type="email" id="email" name="email" placeholder="이메일" value="${member.email}"><br>
		<input type="submit" class="btn" value="회원 정보 수정" ><br>
		<input type="reset" value="다시 입력">
	</form>
	<form action="removeMemberForm.jsp" method="post">
		<button type="submit" id="removeMember">회원탈퇴</button>
	</form>
</div>
</body>
</html>