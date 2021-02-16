<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>회원가입</title>
</head>
<body>
<div align="center">
    <h2>회원가입</h2>
    <hr/>
    <form:form modelAttribute="member">
        <table border="1" cellpadding="2" cellspacing="0">
            <tr>
                <td><form:label path="id">아이디</form:label></td>
                <td>
                    <form:input path="id"/>
                    <form:errors path="id" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="name">이름</form:label></td>
                <td>
                    <form:input path="name"/>
                    <form:errors path="name" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="age">나이</form:label></td>
                <td>
                    <form:input path="age"/>
                    <form:errors path="age" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td><form:label path="address">주소</form:label></td>
                <td>
                    <form:input path="address"/>
                    <form:errors path="address" element="div" cssStyle="color: red;"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="reset" value="취소"/>
                    <input type="submit" value="가입"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
