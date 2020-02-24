<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복검사</title>
<script>

	function closeFunc(){
		this.close();
	}

	function useBtnFunc(){
		var userid = document.getElementById("userid");
		opener.document.getElementById("userid").value = userid.value;
		this.close();
	}
</script>
</head>
<body>

<form action="<c:url value='/member/idCheck'/>" method="post">
	<span>id</span>
	<input type="text" id="userid" name="userid" value="${userid}">
	<br>
	<input type="submit" value="중복확인">
</form>
	<input type="button" id="useBtn" value="사용" disabled="disabled" onclick="useBtnFunc()">
	<input type="button" value="취소" onclick="closeFunc()">
	<div>
		<c:choose>
			<c:when test="${idCheckResult == true}">
				이미 사용중인 아이디입니다.
				<script>
					document.getElementById("useBtn").addAttribute("disabled");
				</script>
			</c:when>
			<c:when test="${idCheckResult == false}">
				사용가능한 아이디입니다.
				<script>
					document.getElementById("useBtn").removeAttribute("disabled");
				</script>
			</c:when>
		</c:choose>
	</div>
</body>
</html>