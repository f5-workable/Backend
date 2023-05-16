<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<title>경량환경 단순홈페이지 템플릿 - 로그인</title>
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/login.css" rel="stylesheet" type="text/css" >
</head>
<body  onload="fnInit();">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">

$(document).ready(function() {

	$('#id_ck').click(function() {
		
		var id = $('#id').val();
		var data = { "id" : id }
		
		$.ajax({
			 url:'/hbz/id_check.do'
			,data : data
			,type : 'post'
			,dataType : 'json'
			,success:function(result){

				if(result.msg == true){
					alert("사용이 가능한 ID 입니다");
				}else if(result.msg == false){
					alert("중복된 ID 입니다.");
				}
			}
		});
		
	});
})

function addr_find() {
	
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('addr').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("addr").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("addr").value = jibunAddr;
            }
        }
    }).open();
}

function join(){
	// 주소 인증키
	//devU01TX0FVVEgyMDIzMDQxODE0MjIzMjExMzY5OTA=
		
	if($('#id').val() == ''){
		alert('id를 입력하세요');
	}else if($('#password').val() == ''){
		alert("password를 입력하세요.")
	}else if($('#Confirm_password').val() == ''){
		alert("Confirm password를 입력하세요");
	}else if($('#name').val() == ''){
		alert('name을 입력하세요')
	}else if($('#tel').val() == ''){
		alert('tel을 입력하세요');
	}else if($('#email').val() == ''){
		alert('email을 입력하세요');
	}else if($('#addr').val() == ''){
		alert('address를 입력하세요');
	}else if($('#password').val() != $('#Confirm_password').val()){
		alert('패스워드가 서로 일치하지 않습니다');
	}else if(!($('#ck_box').is(':checked'))){
		alert("개인정보 동의를 해야 가입이 가능합니다.")
	}else {
		if (confirm('회원가입 하시겠습니까?')) {
			insertForm.submit();
		}
	}
};
</script>
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
                    <br>
                    <form:form name="insertForm" method="post" action="/hbz/insertAction.do">
                     <label for="exampleInputEmail1" class="form-label">ID</label>
					  <div class="input-group mb-3">
					    <input type="text" class="form-control" id="id" name="id" />
					    <input type="button" class="btn btn-primary" id="id_ck" value="ID Check">
					  </div>
					  <div class="mb-3">
					    <label for="exampleInputPassword1" class="form-label">Password</label>
					    <input type="password" class="form-control" id="password" name="password"/>
					  </div>
					  <div class="mb-3">
					    <label for="exampleInputPassword1" class="form-label">Confirm password</label>
					    <input type="password" class="form-control" id="Confirm_password" name="Confirm_password"/>
					  </div>
					  <div class="mb-3">
					    <label for="exampleInputEmail1" class="form-label">Name</label>
					    <input type="text" class="form-control" id="name" name="name"/>
					  </div>
					  <div class="mb-3">
					    <label for="exampleInputEmail1" class="form-label">Tel</label>
					    <input type="text" class="form-control" id="tel" name="tel"/>
					  </div>
					  <div class="mb-3">
					    <label for="exampleInputEmail1" class="form-label">Email</label>
					    <input type="text" class="form-control" id="email" name="email" />
					  </div>
					  <label for="exampleInputEmail1" class="form-label">Address</label>
					  <div class="input-group mb-3">
					    <input type="text" class="form-control" id="addr" name="addr" readonly />
					    <input type="button" onclick="addr_find()" class="btn btn-primary" value="주소찾기"/>
					  </div>
					  <div class="mb-3 form-check">
					    <input type="checkbox" class="form-check-input" id="ck_box">
					    <label class="form-check-label" for="exampleCheck1">개인정보공개 동의</label>
					  </div>
					  
					</form:form>
					<button onclick="join()" class="btn btn-primary">Join</button>
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
