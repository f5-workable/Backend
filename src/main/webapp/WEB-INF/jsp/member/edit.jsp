<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Edit Member</title>
</head>
<body>
	<h1>Edit Member</h1>
	<form action="/member/edit" method="post">
		<label for="id">ID:</label> 
		<input type="text" id="id" name="id" value="${memberDTO.id}" readonly /><br /><br /> 
		
		<label for="password">Password:</label>
		<input type="text" id="password" name="password" value="${memberDTO.password}" /><br /><br /> 
		
		<label for="email">Email:</label> 
		<input type="email" id="email" name="email" value="${memberDTO.email}" /><br /><br /> 
		
		<label for="name">Name:</label> 
		<input type="text" id="name" name="name" value="${memberDTO.name}" /><br /><br /> 
		
		<label for="phone">Phone:</label>
		<input type="text" id="phone"	name="phone" value="${memberDTO.phone}" /><br /><br /> 
		
		<label for="profil">Profil:</label> 
		<input type="text" id="profil" name="profil" value="${memberDTO.profil}" /><br /><br /> 
		
		<input type="submit" value="Submit" />
	</form>
	<a href="/member/list">Member List</a>
</body>
</html>