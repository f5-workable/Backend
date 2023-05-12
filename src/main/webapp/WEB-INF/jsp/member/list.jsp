<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Member List</title>
</head>
<body>
    <h1>Member List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <%-- <c:forEach var="member" items="${memberList}">
                <tr>
                    <td>${member.id}</td>
                    <td>${member.name}</td>
                    <td>${member.email}</td>
                    <td>
                        <a href="/member/edit/${member.id}">Edit</a>
                        <a href="/member/delete?id=${member.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach> --%>
        </tbody>
    </table>
    <br>
    <a href="/member/add">Add Member</a>
    <br>
    <a href="/company/list">Company List</a>
</body>
</html> 