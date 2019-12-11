<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>视频上传</title>

</head>
<body>
<h3 align="center">登录页面</h3>
<hr>
<!-- action代表了服务器端的处理程序 -->
<form action="loginServlet.do" method="post" >

    <table align="center">
        <tr>
            <td>
                姓名
            </td>
            <td>
                <input type="text" name="username"/>
            </td>
        </tr>
        <tr>
            <td>
                密码：
            </td>
            <td>
                <input type="password" name="password"/>
            </td>
        </tr>
        <%String errorInfo = (String)request.getAttribute("msg");         // 获取错误属性
            if(errorInfo != null)
            {
        %>
        <script type="text/javascript" language="javascript">
            alert("<%=errorInfo%>");
        </script>
        <%
            }
        %>
        <tr>
            <td>
                <input type="submit" value="登录" />

            </td>

        </tr>
    </table>

</form>
<form action="registerServlet.do" method="get" >
    <td>
        <input type="submit" value="注册" />

    </td>
</form>
</body>
</html>

