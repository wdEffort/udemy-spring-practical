<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>스프링 시큐리티 커스텀 로그인</title>
</head>
<body>
<div align="center">
    <h2>로그인</h2>
    <hr/>
    <!--
    스프링 시큐리티에서 제공하는 action 속성 값을 이용한다.
    스프링 시큐리티 4 버전 이전에는 '/j_spring_security_check'를 사용했으나,
    그 이후 버전부터는 '/login'을 사용하도록 한다.
    -->
    <form action="<c:url value="${pageContext.request.contextPath}/login"/>" method="post">
        <%--<sec:csrfInput/>--%>
        <%--<inupt type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        <table border="1" cellpadding="3" cellspacing="0">
            <!-- 로그인이 실패 했을 때 출력되는 태그 -->
            <c:if test="${param.fail ne null}">
                <tr>
                    <td colspan="2" align="center" style="color:red;">
                        로그인에 실패했습니다.
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>아이디</td>
                <td>
                    <!--
                    스프링 시큐리티에서 제공하는 name 속성 값을 사용한다.
                    스프링 시큐리티 4 버전 이전에는 'j_username'를 사용했으나,
                    그 이후 버전부터는 'username'을 사용하도록 한다.
                    -->
                    <input type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                    <!--
                    스프링 시큐리티에서 제공하는 name 속성 값을 사용한다.
                    스프링 시큐리티 4 버전 이전에는 'j_password'를 사용했으나,
                    그 이후 버전부터는 'password'을 사용하도록 한다.
                    -->
                    <input type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="로그인"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
