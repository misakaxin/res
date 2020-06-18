<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>


<form action="resuser.do" method="post">
    <input type="hidden" name="op" value="login">


    用户登陆<hr />
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username" /></td>
        </tr>

        <tr>
            <td>密码:</td>
            <td><input type="password" name="pwd"/></td>
        </tr>

        <tr>
            <td>验证码</td>
            <td><input type="text" name="valicode"/> <img src="validCode.jsp" /> <a href="">刷新</a></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登陆"/>
            </td>
        </tr>
    </table>

    ${msg }

</form>


<%@include file="bottom.jsp" %>
