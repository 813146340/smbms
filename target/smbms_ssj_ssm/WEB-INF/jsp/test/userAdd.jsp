<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 2020/8/7
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fm:form modelAttribute="user" action="${pageContext.request.contextPath}/test/checkview" method="get">
    <table>
        <tr>
            <td>用户名</td>
            <td>
                <fm:input path="userName"/>
                <%--类似于js中的错误提示--%>
                <fm:errors path="userName"/>
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <fm:input path="userPassword"/>
                <fm:errors path="userPassword"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交">
            </td>
            <td></td>
        </tr>
    </table>
</fm:form>
<form action="${pageContext.request.contextPath}/test/upload" enctype="multipart/form-data" method="post">
    <input name="file" type="file">
    <button type="submit">提交</button>
</form>
</body>
</html>
