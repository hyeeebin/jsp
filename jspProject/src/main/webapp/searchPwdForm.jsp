<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
<div id="page">
	<h1>비밀번호 찾기</h1>
	<form name="searchPwd" method="post" action="searchPwd">
		<div class="searchId_title">
			<h3>본인 확인</h3>
		</div>
		<div class="searchPwd_form">
			<input type="text" id="id" name="id" placeholder="아이디"><br>
			<input type="tel" id="phone" name="phone" placeholder="전화번호"><br>
		</div>
		<div class="searchPwd_btn">
			<input type="submit" value="비밀번호 찾기"><br>
			<input type="reset" value="다시 입력"> <br>
			<input type="button" value="로그인" onclick="backToLogin()">
		</div>
	</form>
</div>
</body>
<script>
	function backToLogin() {
		alert('비밀번호 찾기를 중단하고 로그인하시겠습니까? ');
		location.replace("login.jsp");
	}

</script>
</html>
