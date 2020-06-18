<%--
  Created by IntelliJ IDEA.
  User: misakaxincell
  Date: 2019/11/22
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="loginForm" action="">
    <input type="hidden" name="op" value="login"/>
    登陆名:<input type="text" name="username"/><br/>
    密码:&nbsp;&nbsp;&nbsp;<input type="password" name="pwd" /><br/>
    <a href="javascript:void " onclick="loginFun() ">快速登陆</a>
    <a href="resuser.do?op=toReg">注册</a>

</form>
${msg }
