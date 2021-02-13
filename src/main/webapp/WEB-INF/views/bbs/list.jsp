<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>게시글 목록</title>
</head>
<body>
<div align="center">
    <h2>게시글 목록</h2>
    <hr width="500 " color="red"/>
    <table border="1" cellpadding="0" cellspacing="0" width="500">
        <colgroup>
            <col width="5%"/>
            <col width=""/>
            <col width="10%"/>
            <col width="15%"/>
            <col width="5%"/>
        </colgroup>
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bbs" items="${bbsList}">
            <tr>
                <td>${bbs.bbsId}</td>
                <td>
                    <c:forEach begin="1" end="${bbs.bbsIndent}">ㄴ</c:forEach>
                    <a href="${pageContext.request.contextPath}/bbs/view?bbsId=${bbs.bbsId}">${bbs.bbsSbj}</a>
                </td>
                <td>${bbs.bbsName}</td>
                <td>${bbs.bbsDate}</td>
                <td>${bbs.bbsHit}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" align="center">
                <a href="${pageContext.request.contextPath}/bbs/write">글쓰기</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
