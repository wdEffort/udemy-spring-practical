<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>스프링 시큐리티 커스텀 로그인 완료</title>
</head>
<body>
<!-- 스프링 시큐리티 컨텍스트에 ID가 비어있지 않은 경우 로그인 상태 출력 -->
<c:if test="${not empty pageContext.request.userPrincipal}">
    <div align="center">
        <h2>${pageContext.request.userPrincipal.name}님 반갑습니다.</h2>
        <hr/>
        <a href="${pageContext.request.contextPath}/logout">로그아웃</a>
    </div>
</c:if>

<!-- 스프링 시큐리티 컨텍스트에 ID가 비어있다면 로그아웃 상태 출력 -->
<c:if test="${empty pageContext.request.userPrincipal}">
    <div align="center">
        <h2>로그아웃 되었습니다.</h2>
        <hr/>
    </div>
</c:if>
</body>
</html>
