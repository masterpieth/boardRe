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
<h1>글 상세</h1>
<span>작성자</span><span>${requestScope.board.userid}</span><br>
<span>글제목</span><span>${requestScope.board.title}</span><br>
<span>조회수</span><span>${requestScope.board.hit}</span><br>
<span>첨부파일</span>
<span>
<a href="<c:url value="/board/download?boardnum=${requestScope.board.boardnum}"/>">${requestScope.board.originalFileName}</a>
</span><br>
<span>내용</span><span>${requestScope.board.content}</span><br>
<c:if test="${sessionScope.userid ==  requestScope.board.userid}">
<a href="<c:url value="/board/updateBoardForm?boardnum=${requestScope.board.boardnum}"/>"><input type="button" value="글수정"></a>
<a href="<c:url value="/board/deleteBoard?boardnum=${requestScope.board.boardnum}"/>"><input type="button" value="글삭제"></a>
</c:if>
<a href="<c:url value="/board/boardList"/>"><input type="button" value="돌아가기"></a>

<form action="<c:url value="/board/writeReply"/>" method="post">
	<input type="text" name="replytext">
	<input type="hidden" name="boardnum" value="${requestScope.board.boardnum}">
	<input type="submit" value="작성">
</form>
</body>
</html>