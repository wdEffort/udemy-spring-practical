<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>게시글 답변 작성</title>
</head>
<body>
<div align="center">
    <h2>게시글 답변 작성</h2>
    <hr width="500 " color="red"/>
    <form action="${pageContext.request.contextPath}/bbs/reply" method="post">
        <input type="hidden" name="bbsId" id="bbsId" value="${bbsVO.bbsId}"/>
        <input type="hidden" name="bbsGroup" id="bbsGroup" value="${bbsVO.bbsGroup}"/>
        <input type="hidden" name="bbsStep" id="bbsStep" value="${bbsVO.bbsStep}"/>
        <input type="hidden" name="bbsIndent" id="bbsIndent" value="${bbsVO.bbsIndent}"/>
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
                <td><label for="bbsName">작성자</label></td>
                <td><input type="text" name="bbsName" id="bbsName" size="20"/></td>
            </tr>
            <tr>
                <td><label for="bbsSbj">제목</label></td>
                <td><input type="text" name="bbsSbj" id="bbsSbj" size="50" value="[RE] ${bbsVO.bbsSbj}"/></td>
            </tr>
            <tr>
                <td><label for="bbsCtt">답변</label></td>
                <td><textarea name="bbsCtt" id="bbsCtt" cols="100" rows="8"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="${pageContext.request.contextPath}/bbs/list">목록보기</a>
                    <input type="submit" value="작성하기"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
