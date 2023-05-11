<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Company</title>
</head>
<body>
	<h1>Edit Company</h1>
	<form action="/company/edit" method="post">
		<label for="c_id">기업 ID:</label>
        <input type="text" id="c_id" name="c_id" value="${companyVO.c_id}" /><br/><br/>
        
        <label for="password">기업 Password:</label>
        <input type="text" id="password" name="password" value="${companyVO.password}" /><br/><br/>
        
        <label for="c_name">기업 Name:</label>
        <input type="text" id="c_name" name="c_name" value="${companyVO.c_name}" /><br/><br/>
        
        <label for="rnum">사업자 번호:</label>
        <input type="text" id="rnum" name="rnum" value="${companyVO.rnum}" /><br/><br/>

        <label for="address">주소:</label>
        <input type="text" id="address" name="address" value="${companyVO.address}" /><br/><br/>

        <label for="logo">LOGO:</label>
        <input type="text" id="logo" name="logo" value="${companyVO.logo}" /><br/><br/>
        
        <label for="company_type">기업 형태:</label>
        <input type="text" id="company_type" name="company_type" value="${companyVO.company_type}" /><br/><br/>
        
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${companyVO.phone}" /><br/><br/>

        <input type="submit" value="Submit"/>
	</form>
	<a href="/company/list">Company List</a>
</body>
</html>