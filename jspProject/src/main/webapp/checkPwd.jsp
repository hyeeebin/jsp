<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 접속 전 비밀번호 확인</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
<div id="page">
<div id="PageHeader">
	<h1>MYPAGE</h1>
</div>

<div id="pageCenter">
	<%
	String id = (String) session.getAttribute("login.id");
	System.out.println("마이페이지 접속 전 비밀번호 확인");

	if (id == null) {
		response.sendRedirect("login.jsp");
	}
	%>

	<form action="checkPwd" name="checkPwd" method="post"
		onsubmit="return finalCheck();">
		<input type="hidden" value="<%=id%>" name="id">
		<div class="formRow"><h3>비밀번호 입력 후 마이페이지 조회가 가능합니다.</h3></div>
		<br>
		<div class="formRow">
			<input id="pwd" type="password" name="pwd"
				placeholder="비밀번호를 한번 더 입력해주세요">
		</div>
		<button type="submit" id="btn">확인</button>
	</form>
</div>
</div>
</body>
<script>
	function finalCheck() {
		var checkPass = document.checkPwd;

		if (0 >= checkPass.pwd.value.length) {
			// 비밀번호가 입력되지 않았으면 false
			alert("비밀번호를 입력해 주세요!");
			checkPass.pwd.focus();
			return false;
		}
	}
</script>
</html>