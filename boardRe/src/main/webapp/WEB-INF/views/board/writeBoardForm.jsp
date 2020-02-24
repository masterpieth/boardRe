<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="<c:url value="/board/writeBoard"/>" method="post" enctype="multipart/form-data">
		<h1>글 작성</h1>
		<div>
			<span>ID</span><input type="text" name="userid"
				value="${sessionScope.userid}" readonly="readonly">
		</div>
		<div>
			<span>제목</span><input type="text" name="title" placeholder="제목 입력">
		</div>
		<div>
			<span>첨부파일</span><input type="file" name="uploadFile">
		</div>
		<div>
			<span>내용</span>
			<textarea rows="4" cols="40" name="content"></textarea>
		</div>
	<input type="submit" value="작성">
	
	<input type="button" value="취소" onclick="history.go(-1)">
	</form>

</body>
</html>