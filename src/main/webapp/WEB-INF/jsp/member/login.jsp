<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="/member/login.do">
		<p>
			<label for="id">아이디:</label> <input type="text" name="id" id="id"
				required="true" />
		</p>
		<p>
			<label for="password">비밀번호:</label> <input type="password"
				name="password" id="password" required="true" />
		</p>
		<c:if test="${not empty errorMessage}">
			<p style="color: red;">${errorMessage}</p>
		</c:if>
		<p>
			<input type="submit" value="로그인" />
		</p>
	</form>
	<p>
		아직 회원이 아니신가요? <a href="/member/signup.do">회원 가입</a>
	</p>
</body>
</html>