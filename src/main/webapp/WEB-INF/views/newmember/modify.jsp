<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>회원정보수정</title>
</head>
<body>
<div align="center">
    <h2>회원정보수정</h2>
    <hr width="500"/>
    <form:form modelAttribute="newMember" action="${pageContext.request.contextPath}/newmember/modify" method="post">
        <form:hidden path="id"/>
        <table border="1" cellpadding="2" cellspacing="0" width="500">
            <tbody>
            <tr>
                <td>아이디</td>
                <td>${newMember.id}</td>
            </tr>
            <tr>
                <td><form:label path="pwd">비밀번호</form:label></td>
                <td>
                    <form:password path="pwd" maxlength="16"/>
                    <form:errors path="pwd" element="div" cssStyle="color: red;"/>

                </td>
            </tr>
            <tr>
                <td><form:label path="name">이름</form:label></td>
                <td>
                    <form:input path="name" maxlength="5"/>
                    <form:errors path="name" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="tel">연락처</form:label></td>
                <td>
                    <form:input path="tel" maxlength="13"/>
                    <form:errors path="tel" element="div" cssStyle="color: red;"/>

                </td>
            </tr>
            <tr>
                <td><form:label path="email">이메일</form:label></td>
                <td>
                    <form:input path="email" maxlength="100"/>
                    <form:errors path="email" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="reset" value="취소"/>
                    <input type="submit" value="수정하기"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
</div>
</body>
</html>
