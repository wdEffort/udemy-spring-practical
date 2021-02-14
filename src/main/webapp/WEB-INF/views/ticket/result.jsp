<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>티켓 구매완료</title>
</head>
<body>
<div align="center">
    <h2>티켓 구매완료</h2>
    <hr width="500" color="green"/>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>구매자</td>
            <td>${buyVO.userName}</td>
        </tr>
        <tr>
            <td>티켓 매수</td>
            <td>${buyVO.amount}매</td>
        </tr>
    </table>
</div>
</body>
</html>
