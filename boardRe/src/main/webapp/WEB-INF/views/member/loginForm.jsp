<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>
	function goMain(){
		location.href="<c:url value='/'/>";
		}
</script>
</head>
<body>

<form action="<c:url value='/member/login'/>" method="post">
	<span>id</span><input type="text" name="userid"><br>
	<span>pw</span><input type="password" name="userpwd"><br>
	<input type="submit" value="로그인"><input type="button" value="돌아가기" onclick="goMain()">
</form>
</body>
</html>