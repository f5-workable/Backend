<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보</title>
</head>
<body>
	<nav>
		<ul>
			<li><a href="/member/home.do">홈</a></li>
			<li><a href="/member/info.do/${id}">상세 정보</a></li>
			<%-- 사용자가 로그인한 경우에만 로그아웃 링크를 보여줍니다. --%>
			<%
			if (request.isUserInRole("ROLE_MEMBER")) {
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
	
	<h1>상세 정보</h1>
	<p><strong>ID:</strong> ${id}</p>
	<p><strong>Birth:</strong> ${member.birth}</p>
	<p><strong>Name:</strong> ${member.name}</p>
	<p><strong>Email:</strong> ${member.email}</p>
	<p><strong>Phone:</strong> ${member.phone}</p>
	<p><strong>Profil:</strong> ${member.profil}</p>
	
	<form action="/member/update.do/${member.id}" method="get">
        <button type="submit">수정</button>
    </form>
    
    <form action="/member/delete.do" method="get" onsubmit="return confirm('정말로 탈퇴하시겠습니까?');">
        <button type="submit">탈퇴</button>
    </form>
</body>
</html>