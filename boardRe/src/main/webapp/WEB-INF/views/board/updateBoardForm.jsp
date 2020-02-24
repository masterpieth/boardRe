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

<form action="<c:url value="/board/updateBoard"/>" method="post" enctype="multipart/form-data">
<input type="hidden" name="boardnum" value="${requestScope.board.boardnum}">
<span>작성자</span><input type="text" name="userid" value="${requestScope.board.userid}" readonly="readonly"><br>
<span>글제목</span><input type="text" name="title" value="${requestScope.board.title}"><br>
<span>첨부파일</span><input type="file" name="uploadFile">${requestScope.board.originalFileName}<br>
<span>내용</span><textarea rows="4" cols="40" name="content">${requestScope.board.content}</textarea><br>
<input type="submit" value="글수정">
<a href="<c:url value="/board/readBoard?boardnum=${requestScope.board.boardnum}"/>"><input type="button" value="돌아가기"></a>
</form>
</body>
</html>