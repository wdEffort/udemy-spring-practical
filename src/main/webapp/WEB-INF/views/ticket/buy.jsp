<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>티켓 구매</title>
</head>
<body>
<div align="center">
    <h2>한일전 축구 경기 티켓 구매</h2>
    <hr width="500" color="green"/>
    <form:form modelAttribute="buy">
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td><form:label path="userName">구매자</form:label></td>
                <td><form:input path="userName"/></td>
            </tr>
            <tr>
                <td><form:label path="amount">티켓 매수</form:label></td>
                <td><form:input path="amount" size="2" maxlength="2"/>매</td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="카드결제"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
