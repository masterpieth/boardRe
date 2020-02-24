<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	
	<c:if test="${writeResult == true}">
		<script>
			alert("성공");
		</script>
	</c:if>
	<c:if test="${writeResult == false}">
		<script>
			alert("실패");
		</script>
	</c:if>
	<div>
		<a href="<c:url value="/board/writeBoardForm"/>"><input type="button" value="글 작성"></a>
	</div>
	
	<c:forEach items="${requestScope.boardList}" var="board">
	<span>${board.boardnum}</span>
		<a href="<c:url value="/board/readBoard?boardnum=${board.boardnum}"/>"><span>${board.title}</span></a><br>
	</c:forEach>
	
	<c:if test="${pageMaker.prev}">
	<a href="boardList${pageMaker.makeQuery(pageMaker.startPage -1)}">이전</a>
	</c:if>
	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
		<a href="boardList${pageMaker.makeQuery(idx)}">${idx}</a>
	</c:forEach>
	<c:if test="${pageMaker.next}">
	<a href="boardList${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
	</c:if>
</body>
</html>