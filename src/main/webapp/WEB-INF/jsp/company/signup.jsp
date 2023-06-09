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
	
	<h1>회원가입</h1>
	<form action="/company/signup.do" method="post">
        <p>
            <label>아이디:</label>
            <input type="text" name="c_id" required>
        </p>
        <p>
            <label>비밀번호:</label>
            <input type="password" name="c_password" required>
        </p>
        <p>
            <label>기업 명:</label>
            <input type="text" name="c_name" required>
        </p>
        <p>
            <label>사업자 번호:</label>
            <input type="text" name="rnum" required>
        </p>
        <p>
            <label>주소:</label>
            <input type="text" name="address" required>
        </p>
        <p>
            <label>기업 형태:</label>
            <input type="text" name="c_type" required>
        </p>
        <p>
            <label>전화번호:</label>
            <input type="text" name="phone">
        </p>
        <p>
            <label>로고:</label>
            <input type="text" name="logo">
        </p>
        
        <input type="submit" value="가입하기">
    </form>
</body>
</html>