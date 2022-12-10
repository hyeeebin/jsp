<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) session.getAttribute("login.id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="header">
		<div class="container">
			<a class="logo" href="main.jsp">LEE</a>
			<div id="headerContent" class="headerContent">
				<ul class="headerContentMenu" id="headerContentMenu">
					<%
						System.out.println("session id : " + id);
	
						if (null == id) {
					%>
					<li class="header-item">
						<a href="login.jsp">LOGIN</a>
					</li>
					<li class="header-item">
						<a href="joinFormMember.jsp">JOIN</a>
					</li>
					<%
						} else {
					%>
					
					<li class="header-item">
						<a><%=id%> 님 환영합니다</a>
					</li>
					<li class="header-item">
						<a href="checkPwd.jsp">MYPAGE</a>
					</li>
					<li class="header-item">
						<a href="logout" onclick="logoutCheck()">LOGOUT</a>
					</li>
					<%
						}
					%>
					<li class="header-item">
						<a href="main.jsp">HOME</a>
					</li>
				</ul>
			</div>
		</div>
	</header>
</body>
<script>
function logoutCheck() {
	alert("로그아웃 하시겠습니까?");
}
</script>
</html>