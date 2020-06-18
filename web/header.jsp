<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
    <meta http-equiv=Content-Type content="text/html; charset=gb2312">
    <title>${webName }</title>
    <meta name="keyword" content="${keyword }">
    <meta name="description" content="${description }">
    <link rel="stylesheet" href="css/styles.css" type="text/css" />
    <script type="text/javascript" src="js/jquery-1.8.0.js"></script>
</HEAD>
<body>

<table cellSpacing=0 cellPadding=0 width=776 align=center border=0>
    <tr>
        <td width="220" rowspan="3"><img src="images/jb_logo.jpg"
                                         width="200"></td>
        <td width="556" height="36">&nbsp;</td>
    </tr>

    <tr>
        <TD
                style="FONT-SIZE: 24pt; FILTER: blur(add=1, direction=120, strength=5); WIDTH: 100%; COLOR: #000000; FONT-FAMILY: arial; POSITION: relative"
                noWrap><B><span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我学我会</span>
            网上订餐系统</B>
        </TD>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>

<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
    <TBODY>
    <TR>
        <TD background="images/001.gif" height=42>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                <TR>
                    <TD width=0 height=20></TD>
                    <TD align=middle><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
                            href="#" target=_self><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000" onClick="location.href='index.jsp'">网站首页</FONT></A><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
                            href="#" target=_self><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">关于我们</FONT></A><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
                            href="#" target=_self><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">定餐帮助</FONT></A><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
                            href="#" target=_self><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">网上定餐</FONT></A><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT><A
                            href="#" target=_self><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">客服中心</FONT></A><FONT
                            style="FONT-SIZE: 13px; COLOR: #000000">&nbsp;|&nbsp;</FONT></TD>
                </TR>
                </TBODY>
            </TABLE>
        </TD>
    </TR>
    </TBODY>
</TABLE>

<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
    <TBODY>
    <TR vAlign=top>
        <TD width=181 background="images/002.gif">
            <TABLE cellSpacing=0 cellPadding=0 width=181 border=0>
                <TBODY>
                <TR>
                    <TD><IMG height=234 src="images/left_top.jpg" width=181></TD>
                </TR>
                </TBODY>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                <TR>
                    <TD align=middle height=30><FONT color=#000000>
                        <SCRIPT
                                language=javascript src="images/DateTime2.js"></SCRIPT>
                        <SCRIPT
                                language=javascript>tick('cn');</SCRIPT>
                    </FONT></TD>
                </TR>
                </TBODY>
            </TABLE>

            <div>
                今天是:<%
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                out.println(sdf.format(new Date()));
                %>
                <hr/>

                <div id="loginStatusDiv">

                </div>

                <hr/>
            </div>
        <TD>

        <script type="text/javascript">
            function loginFun() {
                $.ajax({
                    url:"resuser.do",
                    dataType:"html",
                    data:$("#loginForm").serialize(),
                    type:"post",
                    success:function (data) {
                        $("#loginStatusDiv").html(data);
                    }
                });
            }
            $(function () {
                $.ajax({
                    url:"resuser.do?op=isUserLogin",
                    dataType:"html",
                    type:"post",
                    success:function (data) {
                        $("#loginStatusDiv").html(data);
                    }
                })
            });
        </script>






