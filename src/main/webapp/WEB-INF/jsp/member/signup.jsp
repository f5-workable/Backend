<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
	
	<h1>회원가입</h1>
	<form action="/member/signup.do" method="post">
        <p>
            <label>아이디:</label>
            <input type="text" name="id" required>
        </p>
        <p>
            <label>비밀번호:</label>
            <input type="password" name="password" required>
        </p>
        <p>
            <label>생년월일:</label>
            <input type="date" name="birth" required>
        </p>
        <p>
            <label>이름:</label>
            <input type="text" name="name" required>
        </p>
        <p>
            <label>성별:</label>
            <input type="text" name="gender" required>
        </p>
        <p>
            <label>이메일:</label>
            <input type="text" name="email" required>
        </p>
        <p>
            <label>전화번호:</label>
            <input type="text" name="phone">
        </p>
        <p>
            <label>프로필:</label>
            <input type="text" name="profil">
        </p>
        <input type="submit" value="가입하기">
    </form>
</body>
</html>