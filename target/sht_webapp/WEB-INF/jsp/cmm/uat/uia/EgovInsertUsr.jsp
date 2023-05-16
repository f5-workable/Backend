<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<title>경량환경 단순홈페이지 템플릿 - 로그인</title>
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/login.css" rel="stylesheet" type="text/css" >
<script type="text/javascript">

function insertUsr() {
    alert("성공");
	insertForm.submit();
   
}
</script>
</head>
<!-- <body  onload="fnInit();"> -->
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>    
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <!-- header 시작 -->
    <div id="header_mainsize"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncHeader" /></div>
    <div id="topnavi"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncTopnav" /></div>
    <!-- //header 끝 -->
    <!-- container 시작 -->
    <div id="container">
        <!-- 좌측메뉴 시작 -->
        <div id="leftmenu"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncLeftmenu" /></div>
        <!-- //좌측메뉴 끝 -->
            <!-- content 시작 --> 
            <div id="content">
                <div id="cur_loc">
                    <div id="cur_loc_align">
                        <ul>
                            <li>HOME</li>
                            <li>&gt;</li>
                            <li><strong>회원가입</strong></li>
                        </ul>
                    </div>                  
                </div>
                <!-- 타이틀 이미지 -->
                    <div class="usr_insert">
                    		<form:form name="insertForm" method="get" action="/hbz/insertAction.do">
                            	<div class="user_login_ultop">
	                           		<table>
	                           			<tr>
	                           				<th>아이디</th>
	                           				<td><input type="text" class="input_style" id="id" name="id" />
	                           			</tr>
	                           			<tr>
	                           				<th>비밀번호</th>
	                           				<td><input type="password" class="input_style" id="password" name="password"/></td>
	                           			</tr>
	                           			<tr>
	                           				<th>이름</th>
	                           				<td><input type="text" class="input_style" id="name" name="name"/></td>
	                           			</tr>
	                           			<tr>
	                           				<th>전화번호</th>
	                           				<td><input type="text" class="input_style" id="tel" name="tel" /></td>
	                           			</tr>
	                           			<tr>
	                           				<th>이메일</th>
	                           				<td><input type="text" class="input_style" id="email" name="email"/></td>
	                           			</tr>
	                           			<tr>
	                           				<th>주소</th>
	                           				<td><input type="text" class="input_style" id="addr" name="addr"/></td>
	                           			</tr>
	                           		</table>
	                           		
	                           		<input type="submit" value="회원가입"/>
	                            </div>
                            </form:form>
                    </div>
            </div>                      
            <!-- //content 끝 -->    
    </div>  
    <!-- //container 끝 -->
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
    <!-- //footer 끝 -->
</div>
<!-- //전체 레이어 끝 -->
</body>
</html>
