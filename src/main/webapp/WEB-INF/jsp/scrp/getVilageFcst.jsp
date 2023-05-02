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

<style type="text/css">
.form-select.form-select-sm {
  font-size: 5px; /* 원하는 폰트 크기로 변경 */
}
</style>

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
            	<form action="/hbz/getVilageFcst.do" method="get">
            	
            		<div class="row">
            			<div class="col">
	            			<select class="form-select form-select-sm" id="in_area" name="in_area">
							 	<option selected value="su">서울</option>
							  	<option value="bs">부산</option>
							  	<option value="dg">대구</option>
							  	<option value="gj">광주</option>
							  	<option value="dj">대전</option>
							  	<option value="ic">인천</option>
							</select>
            			</div>
            			<div class="col">
            				<select class="form-select form-select-sm" id="in_time" name="in_time">
							 	<option selected value="0600">0600</option>
							  	<option value="0700">0700</option>
							  	<option value="0800">0800</option>
							  	<option value="0900">0900</option>
							  	<option value="1000">1000</option>
							  	<option value="1100">1100</option>
							  	<option value="1200">1200</option>
							  	<option value="1300">1300</option>
							  	<option value="1400">1400</option>
							  	<option value="1500">1500</option>
							  	<option value="1600">1600</option>
							  	<option value="1700">1700</option>
							  	<option value="1800">1800</option>
							  	<option value="1900">1900</option>
							  	<option value="2000">2000</option>
							  	<option value="2100">2100</option>
							  	<option value="2200">2200</option>
							  	<option value="2300">2300</option>
							  	<option value="0000">0000</option>
							</select>
            			</div>
            			<div class="col">
            				<button class="btn btn-primary btn-sm" type="submit">조회</button>
            			</div>
            		</div>
				</form>
            <br>
			<table class="table table-striped table-hover table-light" style="width: 80%;" border="1">
				<thead>
					<tr class="table table-light table-sm font-weight-bold" >
						<th class="text-left font-weight-bold" 	scope="col" style="width: 30%;">항목명</th>
						<th class="font-weight-bold" 			scope="col" style="width: 50%;">항목값</th>
						<th class="font-weight-bold" 			scope="col" style="width: 20%;">단위</th>
					</tr>
				</thead>
				<tbody>
					<tr class="table-light">
						<td scope="col">강수확률</td>
						<td scope="col">${map.POP}</td>
						<td scope="col">%</td>
					</tr>
					<tr class="table-light">
						<td scope="col">강수형태</td>
						<td scope="col">${map.PTY}</td>
						<td scope="col">코드값</td>
					</tr>
					<tr class="table-light">
						<td scope="col">1시간 강수량</td>
						<td scope="col">${map.PCP}</td>
						<td scope="col">범주 (1mm)</td>
					</tr>
					<tr class="table-light">
						<td scope="col">습도</td>
						<td scope="col">${map.REH}</td>
						<td scope="col">%</td>
					</tr>
					<tr class="table-light">
						<td scope="col">1시간 신적설</td>
						<td scope="col">${map.SNO}</td>
						<td scope="col">범주 (1mm)</td>
					</tr>
					<tr class="table-light">
						<td scope="col">하늘상태</td>
						<td scope="col">${map.SKY}</td>
						<td scope="col">코드값</td>
					</tr>
					<tr class="table-light">
						<td scope="col">1시간 기온</td>
						<td scope="col">${map.TMP}</td>
						<td scope="col">℃</td>
					</tr>
					<tr class="table-light">
						<td scope="col">일 최저기온</td>
						<td scope="col">${map.TMN}</td>
						<td scope="col">℃</td>
					</tr>
					<tr class="table-light">
						<td scope="col">일 최고기온</td>
						<td scope="col">${map.TMX}</td>
						<td scope="col">℃</td>
					</tr>
					<tr class="table-light">
						<td scope="col">풍속(동서성분)</td>
						<td scope="col">${map.UUU}</td>
						<td scope="col">m/s</td>
					</tr>
					<tr class="table-light">
						<td scope="col">풍속(남북성분)</td>
						<td scope="col">${map.VVV}</td>
						<td scope="col">m/s</td>
					</tr>
					<tr class="table-light">
						<td scope="col">파고</td>
						<td scope="col">${map.WAV}</td>
						<td scope="col">M</td>
					</tr>
					<tr class="table-light">
						<td scope="col">풍향</td>
						<td scope="col">${map.VEC}</td>
						<td scope="col">deg</td>
					</tr>
					<tr class="table-light">
						<td scope="col">풍속</td>
						<td scope="col">${map.WSD}</td>
						<td scope="col">m/s</td>
					</tr>
				</tbody>
			</table>
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
