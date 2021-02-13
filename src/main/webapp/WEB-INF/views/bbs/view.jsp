<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>게시글 보기</title>
</head>
<body>
<div align="center">
    <h2>게시글 보기</h2>
    <hr width="500 " color="red"/>
    <form:form modelAttribute="bbsVO" action="${pageContext.request.contextPath}/bbs/modify" method="post">
        <form:hidden path="bbsId" value="${bbsVO.bbsId}"/>
        <table border="1" cellpadding="0" cellspacing="0" width="500">
            <colgroup>
                <col width="15%"/>
                <col width=""/>
            </colgroup>
            <tbody>
            <tr>
                <td>글 번호</td>
                <td>${bbs.bbsId}</td>
            </tr>
            <tr>
                <td>조회수</td>
                <td>${bbs.bbsHit}</td>
            </tr>
            <tr>
                <td><form:label path="bbsName">작성자</form:label></td>
                <td><form:input path="bbsName" size="20" value="${bbsVO.bbsName}"/></td>
            </tr>
            <tr>
                <td><form:label path="bbsSbj">제목</form:label></td>
                <td><form:input path="bbsSbj" size="50" value="${bbsVO.bbsSbj}"/></td>
            </tr>
            <tr>
                <td><form:label path="bbsCtt">내용</form:label></td>
                <td><form:textarea path="bbsCtt" cols="100" rows="8">${bbsVO.bbsCtt}</form:textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="${pageContext.request.contextPath}/bbs/list">목록보기</a>
                    <input type="submit" value="수정하기"/>
                    <a href="${pageContext.request.contextPath}/bbs/delete?bbsId=${bbsVO.bbsId}">삭제하기</a>
                    <a href="${pageContext.request.contextPath}/bbs/reply?bbsId=${bbsVO.bbsId}">답변하기</a>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
</div>
</body>
</html>
