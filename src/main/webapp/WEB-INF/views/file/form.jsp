<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>파일 업로드</title>
</head>
<body>
<div align="center">
    <h2>파일 업로드</h2>
    <hr width="500" color="blue"/>
    <form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td>첨부파일1</td>
                <td><input type="file" name="file1"/></td>
            </tr>
            <tr>
                <td>첨부파일2</td>
                <td><input type="file" name="file2"/></td>
            </tr>
            <tr>
                <td>첨부파일3</td>
                <td><input type="file" name="file3"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="업로드"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
