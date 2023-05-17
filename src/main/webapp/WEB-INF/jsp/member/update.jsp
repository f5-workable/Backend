<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>정보 수정</title>
</head>
<body>
	<nav>
		<ul>
			<li><a href="/member/home.do">홈</a></li>
			<li><a href="/member/info.do/${id}">상세 정보</a></li>
			<%-- 사용자가 로그인한 경우에만 로그아웃 링크를 보여줍니다. --%>
			<%
			if (request.isUserInRole("ROLE_USER")) {
			%>
			<li><a href="/logout">로그아웃</a></li>
			<%
			} else {
			%>
			<li><a href="/member/login.do">로그인</a></li>
			<%
			}
			%>
		</ul>
	</nav>
	
    <h2>정보 수정</h2>
    <form method="post" action="/member/update.do/${id}">
        <input type="hidden" name="_method" value="post"> <!-- Use _method to override POST as Spring doesn't support PUT directly -->
        
        <label for="name">ID:</label>
        <input type="text" id="id" name="id" value="${id}" required readonly><br>
        <label for="name">Password:</label>
        <input type="password" id="password" name="password" value="${member.password}" required readonly><br>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${member.name}" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${member.email}" required><br>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${member.phone}" required><br>
        <label for="Profil">Profil:</label>
        <input type="text" id="Profil" name="Profil" value="${member.profil}"><br>
        
        <input type="submit" value="수정">
    </form>
</body>
</html>