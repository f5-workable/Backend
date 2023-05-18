<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<body>
	<nav>
		<ul>
			<li><a href="/company/home.do">홈</a></li>
			<li><a href="/company/info.do/${id}">상세 정보</a></li>
			<%-- 사용자가 로그인한 경우에만 로그아웃 링크를 보여줍니다. --%>
			<%
			if (request.isUserInRole("ROLE_COMPANY")) {
			%>
			<li><a href="/logout">로그아웃</a></li>
			<%
			} else {
			%>
			<li><a href="/company/login.do">로그인</a></li>
			<%
			}
			%>
		</ul>
	</nav>
	
	<h1>홈</h1>
	<p>Logged in as: ${id}</p>
</body>
</html>