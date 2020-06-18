<%--
  Created by IntelliJ IDEA.
  User: misakaxincell
  Date: 2019/11/20
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
欢迎您:${resuser.username }<br/>
&nbsp;&nbsp; <a href="">更改密码</a> <br/>
&nbsp;&nbsp; <a href="">查看历史</a> <br/>
&nbsp;&nbsp; <a href="">收藏夹</a> <br/>
&nbsp;&nbsp; <a href="">地址管理</a> <br/>
&nbsp;&nbsp; <a href="">积分</a> <br/>
&nbsp;&nbsp; <a href="javascript:void" onclick="logout()">安全退出</a> <br/>

<hr/>

<script type="text/javascript">
    function logout() {
        $.ajax({
            url:"resuser.do?op=logout",
            type:"post",
            dataType:"html",
            success:function (data) {
                $("#loginStatusDiv").html(data);
            }
        })
    }
</script>

