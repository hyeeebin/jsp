<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%
request.setCharacterEncoding("UTF-8");

Member member = (Member)request.getAttribute("member");
System.out.println(member);
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
<div id="page">
	<form>
		<%-- 		<%
		if (id != null) {
		%> --%>

		<div class="container">
			<div class="found-success">
				<h2>회원님의 아이디는</h2>
				<h2>
					<input type="text" id="id" name="id" readonly="readonly" value="${id}" ><br>
				</h2>
				<h2>입니다</h2>
			</div>
			<div class="found-login">
				<input type="button" id="btnLogin" value="로그인" onClick='login()' />
			</div>
		</div>
		<%-- 		<%
		} else {
		%> --%>
		<%-- 		<div class="container">
			<div class="found-fail">
				<h4>등록된 정보가 없습니다</h4>
			</div>
			<div class="found-login">
				<input type="button" id="btnback" value="다시 찾기" onClick="history.back()" /> 
				<input type="button" id="btnjoin" value="회원가입" onClick="join()" />
			</div>
		</div>
		<%
		}
		%> --%>
	</form>
</div>
</body>
<script>
	function login() {
		location.replace("login.jsp");
	}
	function join() {
		location.replace("joinFormMember.jsp");
	}
</script>
</html>