<%--
  Created by IntelliJ IDEA.
  User: 11215
  Date: 2019/11/30
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String videoName = (String) request.getSession().getAttribute("videoName");
    Integer videoId = (Integer) request.getSession().getAttribute("videoId");
%>
<html>
<head>
    <title>comment</title>
</head>
<script type="text/javascript"></script>
<body>
<form action="/LogoutServlet" method="post">
    <td>
        <input type="submit" value="退出登录"></td>
</form>

<video id="video" controls preload="auto" width="400px" height="300px" >
    <source src="${src}" type="video/mp4" align="center">
</video>

<table width="600" border="1" cellpadding="0" align="center">
    <tr>
        <th>ID</th>
        <th>评论内容</th>
    </tr>
    <c:forEach var="U" items="${comments}">
        <tr>
            <td><input type="hidden" value="${U.commentUserName}" name="username">${U.commentUserName}</td>
            <td><input type="hidden" value="${U.comment}" name="comment">${U.comment}</td>
        </tr>
    </c:forEach>
    <div align="center" >

        <a href="comment.jsp?videoId=${videoId}&page=1">首页</a>
        <a href="comment.jsp?videoId=${videoId}&page=${prePage}">上一页</a>
        <a href="comment.jsp?videoId=${videoId}&page=${nextPage}">下一页</a>
        <a href="comment.jsp?videoId=${videoId}&page=${totalPage}">尾页</a>

        第${curPage}页/共${totalPage}页
    </div>
</table>
<form action="${pageContext.request.contextPath}/NewCommentServlet" method="post" align="center">
    <table align="center">
        <tr>
        <tr>
        <input  type="hidden" value="<%=videoId%>" name="videoId" />
            <td>
                评论
            </td>
            <td>
                <input type="text" name="comment1" />
            </td>
        </tr>
        <tr>
            <input type="submit" name="Submit" value="提交"/>

            </td>
        </tr>
    </table>
</form>

</body>
</html>
