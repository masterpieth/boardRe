<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.userid == null}">
			<h1>메인화면</h1>
			<a href="<c:url value='/member/signupForm'/>">회원가입</a>
			<a href="<c:url value='/member/loginForm'/>">로그인</a>
			<br>
		</c:when>
		<c:when test="${sessionScope.userid != null}">
			${sessionScope.userid}님 반갑습니다.<br>
			<a href="<c:url value='/board/boardList'/>">게시판</a><br>
			<a href="<c:url value='/member/logout'/>">로그아웃</a>
		</c:when>
	</c:choose>

</body>
</html>