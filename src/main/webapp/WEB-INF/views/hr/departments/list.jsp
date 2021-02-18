<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>부서 목록</title>
</head>
<body>
<div align="center">
    <h2>부서 목록</h2>
    <hr width="500" color="red"/>
    <table width="500" cellpadding="0" cellspacing="0" border="1">
        <thead>
        <tr>
            <th>부서코드</th>
            <th>부서명</th>
            <th>매니저ID</th>
            <th>위치코드</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dept" items="${list}">
            <tr>
                <td>${dept.departmentId}</td>
                <td>${dept.departmentName}</td>
                <td>${dept.managerId}</td>
                <td>${dept.locationId}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
