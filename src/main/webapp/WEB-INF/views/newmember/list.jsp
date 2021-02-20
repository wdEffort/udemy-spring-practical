<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>회원목록</title>
</head>
<body>
<div align="center">
    <h2>회원목록</h2>
    <hr width="500"/>
    <table border="1" cellpadding="2" cellspacing="0" width="500">
        <colgroup>
            <col style="width: 20%"/>
            <col style="width: 20%"/>
            <col style="width: 20%"/>
            <col style="width: 20%"/>
            <col style="width: 20%"/>
        </colgroup>
        <thead>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>연락처</th>
            <th>이메일</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="newMember" items="${newMemberList}">
            <tr>
                <td>${newMember.id}</td>
                <td>${newMember.name}</td>
                <td>${newMember.tel}</td>
                <td>${newMember.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/newmember/modify?id=${newMember.id}">수정</a>
                    <a href="${pageContext.request.contextPath}/newmember/delete?id=${newMember.id}">삭제</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="5" align="center"><a href="${pageContext.request.contextPath}/newmember/join">회원추가</a></td>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
