<%--
  Class Name : EgovIncLeftmenu.jsp
  Description :  좌메뉴 화면(include)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.util.HashMap" %>
<%
String menuNo = ((String)session.getAttribute("menuNo")!=null)?(String)session.getAttribute("menuNo"):"11";
%>

<div id="nav">	
	<div class="top"></div>             
	<div class="nav_style">
	<ul>
		<li class="leftmenu_dept01">
			<a href="/hbz/scrap.do?pageNo=1">스크래핑</a>
			<ul>
				<li class="dept02"><a href="/hbz/scrap.do?pageNo=1">기상청 - 초단기실황</a></li>
				<li class="dept02"><a href="/hbz/getVilageFcst.do?in_area=su&in_time=0600">기상청 - 단기예보</a></li>
				<li class="dept02"><a href="/hbz/newsRank.do">네이버 - 뉴스 랭킹</a></li>
			</ul> 
		</li>
	</ul>
	</div>
	<div class="bottom"></div>		
</div>
<!-- //메뉴 끝 -->	
