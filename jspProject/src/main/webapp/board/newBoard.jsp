<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String id = (String) session.getAttribute("login.id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 게시물 작성 페이지</title>

<link href="../css/style.css" rel="stylesheet" />
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/translations/ko.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
<div id="page1">
	<form method="post" action="../insertBoard" name="Board">
		<label>작성자</label> <input type="text" name="id" id="id" value="<%=id%>" readonly>
		<select name="btype">
			<option  value="일반게시판" selected >일반게시판</option>
			<option  value="공지사항" >공지사항</option>
			<option  value="Q&A">Q&A</option>
		</select><br>
		<label>제목</label> <input type="text" name="title" id="title"><br />
		<label>내용</label><textarea name="content" id="content"></textarea><br>

		<input type="submit" value="전송">
	</form>
	<form  method="post" action="main.jsp">
		<input id ="resettMain" type="reset" value="메인으로 돌아가기">
	</form>
</div>
<script>
        ClassicEditor
            .create( document.querySelector( '#content' ), {language : "ko"} )
            .catch( error => {
                console.error( error );
        } );
</script>
</body>

</html>