<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>게시글 작성</title>
</head>
<body>
<div align="center">
    <h2>게시글 작성</h2>
    <hr width="500 " color="red"/>
    <form:form modelAttribute="bbs">
        <table border="1" cellpadding="0" cellspacing="0" width="500">
            <colgroup>
                <col style="width:20%"/>
                <col />
            </colgroup>
            <tbody>
            <tr>
                <td><form:label path="bbsName">작성자</form:label></td>
                <td><form:input path="bbsName" size="20"/></td>
            </tr>
            <tr>
                <td><form:label path="bbsSbj">제목</form:label></td>
                <td><form:input path="bbsSbj" size="50"/></td>
            </tr>
            <tr>
                <td><form:label path="bbsCtt">내용</form:label></td>
                <td><form:textarea path="bbsCtt" cols="100" rows="8"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="${pageContext.request.contextPath}/bbs/list">목록보기</a>
                    <input type="submit" value="작성하기"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
</div>
</body>
</html>
