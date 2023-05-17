+<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
            <div class="container text-center">
				<br>
				<div class="mb-3">
					<table class="table table-striped table-hover table-light" style="width: 50%;" border="1">
	               		<thead>
	               			<tr class="table-light" align="center">
								<th scope="col" colspan="12">
									${map.img_01} <b>${map.company_01}</b>
								</th>
	               			</tr>
	               		</thead>
	               		<tbody>
	               			<tr class="table-light">
	               				<td class="align-middle"><h1>1</h1></td>
	               				<td class="align-middle"><a href="${map.href_list_01}">${map.title_list_01}</a></td>
	               				<td>${map.img_list_01}</td>
                			</tr>
                			<tr class="table-light">
                				<td class="align-middle"><h1>2</h1></td>
	               				<td class="align-middle"><a href="${map.href_list_02}">${map.title_list_02}</a></td>
	               				<td>${map.img_list_02}</td>
                			</tr>
                			<tr class="table-light">
                				<td class="align-middle"><h1>3</h1></td>
	               				<td class="align-middle"><a href="${map.href_list_03}">${map.title_list_03}</a></td>
	               				<td>${map.img_list_03}</td>
                			</tr>
                			<tr class="table-light">
                				<td class="align-middle"><h1>4</h1></td>
	               				<td class="align-middle"><a href="${map.href_list_04}">${map.title_list_04}</a></td>
	               				<td>${map.img_list_04}</td>
                			</tr>
                			<tr class="table-light">
                				<td class="align-middle"><h1>5</h1></td>
	               				<td class="align-middle"><a href="${map.href_list_05}">${map.title_list_05}</a></td>
	               				<td>${map.img_list_05}</td>
                			</tr>
	               		</tbody>
	               	</table>
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
