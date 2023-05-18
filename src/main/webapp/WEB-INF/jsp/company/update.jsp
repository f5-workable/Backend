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
	
    <h2>정보 수정</h2>
    <form method="post" action="/company/update.do/${id}">
        <input type="hidden" name="_method" value="post"> <!-- Use _method to override POST as Spring doesn't support PUT directly -->
        
        <label for="id">아이디:</label>
        <input type="text" id="id" name="id" value="${id}" required readonly><br>
        
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" value="${company.c_password}" required readonly><br>
        
        <label for="c_name">기업 명:</label>
        <input type="text" id="c_name" name="c_name" value="${company.c_name}" required><br>
        
        <label for="rnum">사업자 번호:</label>
        <input type="text" id="rnum" name="rnum" value="${company.rnum}" required><br>
        
        <label for="address">주소:</label>
        <input type="text" id="address" name="address" value="${company.address}" required><br>
        
        <label for="c_type">기업 형태:</label>
        <input type="text" id="c_type" name="c_type" value="${company.c_type}" required><br>
        
        <label for="phone">전화번호:</label>
        <input type="text" id="phone" name="phone" value="${company.phone}"><br>
        
        <label for="logo">로고:</label>
        <input type="text" id="logo" name="logo" value="${company.logo}"><br>
        
        <input type="submit" value="수정하기">
    </form>
</body>
</html>