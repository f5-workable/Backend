<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
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
            <label>이름:</label>
            <input type="text" name="name" required>
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