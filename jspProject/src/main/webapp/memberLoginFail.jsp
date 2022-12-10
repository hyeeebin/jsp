<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패 페이지</title>
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="./main/header.jsp"></jsp:include>
	<script>
		alert('로그인을 실패하였습니다. 아이디와 비밀번호를 재확인해 주세요');
		//현재 페이지를 새로운 페이지로 덮어씌우기때문에 이전페이지 이동이 없음
		//이전 페이지로 접근할 필요가 없는 경우에 사용
		location.replace("login.jsp");
	</script>
</body>
</html>