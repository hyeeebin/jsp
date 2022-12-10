<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
<div id="page">
	<h1>아이디 찾기</h1>
	<form name="searchId" method="post" action="searchId">
		<div class="searchId_title">
			<h3>본인 확인</h3>
		</div>
		<div class="searchId_form">
			<input type="text" id="name" name="name" placeholder="회원가입 시 등록한 이름"><br>
			<input type="text" id="phone" name="phone" placeholder="회원가입 시 등록한 전화번호"><br>
		</div>
		<div class="searchId_btn">
			<input type="submit" value="아이디 찾기" ><br>
			<input type="reset" value="다시 입력"> <br>
			<input type="button" value="로그인" onclick="backToLogin()">
		</div>
	</form>
</div>
</body>
<script>
function backToLogin() {
	alert('아이디 찾기를 중단하고 로그인하시겠습니까? ');
	location.replace("./login.jsp");
}
</script>
</html>