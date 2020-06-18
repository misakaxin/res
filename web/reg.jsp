<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

    <form action="resuser.do" method="post">
        <input type="hidden" name="op" value="reg" />


    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username" onblur="checkUsername(this.value)"/> <div id="result"></div> </td>
        </tr>

        <tr>
            <td>密码:</td>
            <td><input type="password" name="pwd"/></td>
        </tr>

        <tr>
            <td>验证码:</td>
            <td><input type="text" name="valicode"/><img src="validCode.jsp"/> <a href="">刷新</a> </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册"/>
            </td>
        </tr>
    </table>

    ${msg }

    </form>


<%@include file="bottom.jsp" %>

<script type="text/javascript">
    function checkUsername(username) {
        if (username==null|| username.length<0){
            return;
        }
        $.ajax({
            url:"resuser.do?op=checkusername",
            data:"username="+username,
            type:"post",
            datatype:"json",
            success:function (data) {
                $("#result").html(data.msg);

            }
        })
    }
</script>
