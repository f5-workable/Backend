<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Member</title>
</head>
<body>
    <h1>Add Member</h1>
    <form action="/member/add" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id"/><br/><br/>
        
        <label for="password">Password:</label>
        <input type="text" id="password" name="password"/><br/><br/>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email"/><br/><br/>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name"/><br/><br/>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone"/><br/><br/>
        
        <label for="profil">Profil:</label>
        <input type="text" id="profil" name="profil"/><br/><br/>

        <input type="submit" value="Submit"/>
    </form>
    <a href="/member/list">Member List</a>
</body>
</html>