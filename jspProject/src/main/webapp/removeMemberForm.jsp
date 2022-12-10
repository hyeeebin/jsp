<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
<div id="page">
	<h1>회원 탈퇴</h1>
	<%
	String id = (String) session.getAttribute("login.id");
	if (null == id)
		response.sendRedirect("login.jsp");
	%>
	<form name="checkPass" action="removeMember" method="post">
		<input type="hidden" value="<%=id%>" name="id">
		<div class="formRow"><h3>탈퇴하시려면 비밀번호를 한번 더 입력해 주세요.</h3></div>
		<br>
		<div class="formRow">
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호 입력">
		</div>
		<input type="submit" class="btn" value="확인" onclick="removeCheck()">
	</form>
</div>
</body>
<script>
	function removeCheck(){
		alert("탈퇴시 복구할 수 없습니다. 진행하시겠습니까?");
	}
	function finalCheck() {
		var checkPass = document.checkPass;

		if (0 >= checkPass.pwd.value.length) {
			// 비밀번호가 입력되지 않았으면 false
			alert("비밀번호를 입력해 주세요!");
			checkPass.pwd.focus();
			return false;
		}
	}
</script>
</html>