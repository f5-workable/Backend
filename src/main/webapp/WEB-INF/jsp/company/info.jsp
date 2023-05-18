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
	
	<h1>상세 정보</h1>
	<p><strong>아이디:</strong> ${id}</p>
	<p><strong>기업 명:</strong> ${company.c_name}</p>
	<p><strong>사업자 번호:</strong> ${company.rnum}</p>
	<p><strong>주소:</strong> ${company.address}</p>
	<p><strong>기업 형태:</strong> ${company.c_type}</p>
	<p><strong>전화번호:</strong> ${company.phone}</p>
	<p><strong>로고:</strong> ${company.logo}</p>
	
	<form action="/company/update.do/${id}" method="get">
        <button type="submit">수정</button>
    </form>
    
    <form action="/company/delete.do" method="get" onsubmit="return confirm('정말로 탈퇴하시겠습니까?');">
        <button type="submit">탈퇴</button>
    </form>
</body>
</html>