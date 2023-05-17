<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<title>스크래핑</title>
<link href="<c:url value='/'/>css/common.css" 	rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/login.css" 	rel="stylesheet" type="text/css" >

</head> 
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <!-- header 시작 -->
    <div id="header_mainsize"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncHeader" /></div>
    <div id="topnavi"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncTopnav" /></div>
    <!-- //header 끝 -->
    <!-- container 시작 -->
    <div id="container">
        <!-- 좌측메뉴 시작 -->
        <div id="leftmenu"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncLeftscrapmenu" /></div>
        <!-- 좌측메뉴 끝 -->
            <!-- content 시작 --> 
            <div class="container" id="content">
            <br>
	            <div class="mb-3">
               		<div class="d-flex flex-row-reverse">
               			<a href="/hbz/datapotal.do">
               				<button class="btn btn-primary position-relative btn-sm" > <b>Scraping Start..</b>
               					<span class="position-absolute top-0 start-100 translate-middle p-2 bg-danger border border-light rounded-circle">
    							<span class="visually-hidden">New alerts</span></span>
    						</button>
    					</a>
               		</div>
               		<br>
               		<div class="mb-3">
						<table class="table table-striped table-hover table-light" id="scrap_table" border="1">
		               		<thead>
		               			<tr class="table-light">
									<th scope="col" colspan="12"><b>공공데이터포털 - 기상청 [초단기실황] 데이터 스크래핑</b></th>
		               			</tr>
		               		</thead>
		               		<tbody>
		               			<tr class="table-light">
	                				<th scope="col">번호</th>
	                				<th scope="col">발표일자</th>
	                				<th scope="col">발표시각</th>
	                				<th scope="col">지역</th>
	                				<th scope="col">강수형태</th>
	                				<th scope="col">습도</th>
	                				<th scope="col">1시간 강수량</th>
	                				<th scope="col">기온</th>
	                				<th scope="col">동서바람성분</th>
	                				<th scope="col">풍향</th>
	                				<th scope="col">남북바람성분</th>
	                				<th scope="col">풍속</th>
	                			</tr>
	                			<c:forEach var="list" items="${list}">
	                				<tr class="table-light">
	                					<th scope="row">${list.dataSeq}</th>
	                					<td >${list.baseDate}</td>
	                					<td >${list.baseTime}</td>
	                					<td >${list.area}</td>
	                					<td >${list.pty}</td>
	                					<td >${list.reh}</td>
	                					<td >${list.rn1}</td>
	                					<td >${list.t1h}</td>
	                					<td >${list.uuu}</td>
	                					<td >${list.vec}</td>
	                					<td >${list.vvv}</td>
	                					<td >${list.wsd}</td>
	                				</tr>
	                			</c:forEach>
		               		</tbody>
		               	</table>
	               	</div>
               		<div class="d-flex justify-content-center">
						<c:forEach var="page" begin="1" end="${pageSize}" varStatus="cnt">
							<a href="/hbz/scrap.do?pageNo=${cnt.count}"><input type="button" class="btn btn-secondary btn-sm" value="${cnt.count}"/></a>&nbsp;
						</c:forEach>
					</div>
		        </div>
	         </div>  
		</div>                    
		<!-- //content 끝 -->    
</div>  
<!-- //container 끝 -->
<!-- footer 시작 -->
<div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
<!-- //footer 끝 -->
<!-- //전체 레이어 끝 -->
</body>
</html>
