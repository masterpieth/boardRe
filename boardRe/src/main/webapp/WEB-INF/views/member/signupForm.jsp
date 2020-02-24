<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	function goMain() {
		location.href = "<c:url value='/'/>";
	}
	function openIdCheck() {
		window.open("<c:url value='/member/idCheckForm'/>", "_blank",
				"width=400, height=300");
	}
	function pwdCheck() {
		var userpwd = document.getElementById("userpwd");
		var userpwd2 = document.getElementById("userpwd2");
		var pwdDiv = document.getElementById("pwdDiv");
		if(userpwd.value == userpwd2.value) {
			pwdDiv.innerHTML = "";
			document.getElementById("joinBtn").removeAttribute("disabled");
		} else {
			pwdDiv.innerHTML = "암호가 일치하지 않습니다.";
			document.getElementById("joinBtn").addAttribute("disabled");
		}
	}
</script>
</head>
<body>

	<form action="<c:url value='/member/signup'/>" method="post">
		<span>id</span>
		<input type="text" id="userid" name="userid" readonly="readonly" required="required">
		<input type="button" value="중복확인" onclick="openIdCheck()">
		<br>
		<span>pw</span>
		<input type="password" id="userpwd" name="userpwd" required="required">
		<br>
		<span>pwd확인</span>
		<input type="password" id="userpwd2" name="userpwd2" required="required" onkeyup="pwdCheck()">
		<br>
		<div id="pwdDiv">
		</div>
		<input type="submit" id="joinBtn" value="가입" disabled="disabled">
		<input type="button" value="돌아가기" onclick="goMain()">
	</form>
</body>
</html>