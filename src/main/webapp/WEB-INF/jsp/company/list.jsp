<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Company List</title>
</head>
<body>
    <h1>Company List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <%-- <c:forEach var="company" items="${companyList}">
                <tr>
                    <td>${company.c_name}</td>
                    <td>${company.c_name}</td>
                    <td>${company.c_name}</td>
                    <td>
                        <a href="/company/edit/${company.id}">Edit</a>
                        <a href="/company/delete?id=${company.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach> --%>
        </tbody>
    </table>
    <br>
    <a href="/company/add">Add Member</a>
    <br>
    <a href="/member/list">Member List</a>
</body>
</html> 