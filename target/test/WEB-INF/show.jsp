<%--
  Created by IntelliJ IDEA.
  User: 11215
  Date: 2019/11/23
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath(); //获取项目根目录
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>" rel="external nofollow">
    <title>上传视频</title>
</head>
<body>
<form action="/LogoutServlet" method="post">
    <td>
        <input type="submit" value="退出登录"></td>
</form>
<div class="panel panel-default">
    <div class="panel-body">
        <div class="panel-heading" align="center">
            <h1 class="sub-header h3">视频上传</h1>
        </div>
        <hr>
        <%
            String errorInfo = (String) request.getAttribute("msg");         // 获取错误属性
            if (errorInfo != null) {
        %>
        <script type="text/javascript" language="javascript">
            alert("<%=errorInfo%>");
        </script>
        <%
            }
        %>
        <form class="form-horizontal" id="upload" method="post" action="/uploadServlet.doPost"
              enctype="multipart/form-data">
            <div class="form-group" align="center">
                <tr>
                    <td class="td1">上传视频</td>
                    <td><input type="file" id="photo" name="upload">
                        <input type="submit" value="上传"></td>
                </tr>
            </div>
        </form>
    </div>
</div>
<table width="600" border="1" cellpadding="0" align="center">
    <tr>
        <th>ID</th>
        <th>视频名称</th>
        <th>上传用户</th>
    </tr>
    <c:forEach var="U" items="${videos}">
        <form action="/CommentServlet" method="Get">
            <tr>
                <td><input type="hidden" value="${U.videoId}" name="videoId"/>${U.videoId}</td>
                <td>${U.videoName}</td>
                <td>${U.videoUserName}</td>
                <td><input type="submit" value="详情"/></td>
            </tr>
        </form>
    </c:forEach>
    <div align="center">

        <a href="show.jsp?page=1">首页</a>
        <a href="show.jsp?page=${prePage}">上一页</a>
        <a href="show.jsp?page=${nextPage}">下一页</a>
        <a href="show.jsp?page=${totalPage}">尾页</a>

        第${curPage}页/共${totalPage}页
    </div>
</table>

</body>
</html>