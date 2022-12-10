<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 페이지</title>
<link href="css/login.css" rel="stylesheet" />

</head>
<body>
	<div class="container">
		<div class="wrap">
			<div class="header">
				<h1>WELCOME</h1>
			</div>

			<form name="login" action="login" method="post">
				<div class="loginForm">
					<input type="text" name="id" id="enterId" placeholder="Id" class="input_id" autocomplete="off"><br> 
						<input type="password" name="pwd" id="enterPwd" placeholder="Password" class="input_pwd" autocomplete="off"><br>
					<input type="submit" id="submit" value="로그인">
				</div>

				<div class="wrapOr">
					<hr class="leftHr">
					<div class="or">또는</div>
					<hr class="rightHr">
				</div>

				<div class="searchInfo">
					<div id="searchIdLink">
						<a href="searchIdForm.jsp" id="searchId">아이디 찾기</a><br>
					</div>
					<div id="searchPwdLink">
						<a href="searchPwdForm.jsp" id="searchPwd">비밀번호 찾기</a>
					</div>
				</div>
			</form>
		</div>
		<div class="doJoin">
			<p>
				아직 회원이 아니신가요? <a href="joinFormMember.jsp" class="join">가입하기</a>
			</p>
		</div>
	</div>
</body>
</html>