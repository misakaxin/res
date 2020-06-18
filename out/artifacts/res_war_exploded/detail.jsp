<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
${resfood.fid}


<td>
    <div id="detailDiv">
        <img src="images/${resfood.fphoto}" /><br/>
        商品号:${resfood.fid}<br/>
        品名:${resfood.fname}<br/>
        价格:${resfood.realprice}<br/>

        描述:${resfood.detail}<br/>
            <input type="button" value="加入购物车" onclick="location.href='resfood.do?op=buy&fid=${resfood.fid} '">
            <input type="button" value="加入收藏">
    </div>
</td>


        <%--<TD>--%>
            <%--<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>--%>
                <%--<TBODY>--%>
                <%--<TR>--%>
                    <%--<TD><IMG height=72 src="images/001.jpg" width=595></TD>--%>
                <%--</TR>--%>
                <%--</TBODY>--%>
            <%--</TABLE>--%>
            <%--<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>--%>
                <%--<TBODY>--%>
                <%--<TR>--%>
                    <%--<TD align=right background="images/004.gif" height=19></TD>--%>
                <%--</TR>--%>
                <%--</TBODY>--%>
            <%--</TABLE>--%>
            <%--<TABLE cellSpacing=0 cellPadding=0 width="96%" align=center border=0>--%>
                <%--<TBODY>--%>
                <%--<TR>--%>
                    <%--<TD>--%>

                        <%--<TABLE cellSpacing=1 cellPadding=1 width="100%" align=center--%>
                               <%--bgColor=#c0c0c0 border=0>--%>
                            <%--<TBODY>--%>
                            <%--<TR bgColor=#dadada>--%>

                                <%--<TD width="100%" align="center">我学我会网上点餐系统用户请直接登录</TD>--%>
                            <%--</TR>--%>
                            <%--</TBODY>--%>
                        <%--</TABLE>--%>
                        <%--<BR>--%>
                    <%--</TD>--%>
                <%--</TR>--%>
                <%--</TBODY>--%>
            <%--</TABLE>--%>
            <%--<table width="90%" border="0" align="center" cellpadding="0"--%>
                   <%--cellspacing="0">--%>
                <%--<tr>--%>
                    <%--<td width="90" height="90" valign="top"><img--%>
                            <%--src="images/${resfood.fphoto}" border="0" width="80" height="80"></td>--%>
                    <%--<td valign="top">--%>
                        <%--<table width="98%" border="0" cellspacing="1" cellpadding="0"--%>
                               <%--align="center">--%>
                            <%--<tr>--%>
                                <%--<td><strong>${resfood.fname}</strong></td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td height="21">原价：<strike>人${resfood.normprice}</strike><br>--%>
                                    <%--<font color="#ff0000">现价：${resfood.realpriceString}</font><br>--%>
                                    <%--${resfood.detail} </td>--%>
                            <%--</tr>--%>
                        <%--</table>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td height="30">编号: ${resfood.fid}</td>--%>
                    <%--<td>--%>
                        <%--<table width="145" border="0" cellspacing="1" cellpadding="0">--%>
                            <%--<tr>--%>
                                <%--<td align="center" width="70"><a href=#--%>
                                                                 <%--onClick="window.open('shop_cart.asp?id=500047&nowmenuid=500001','shopcart','width=580,height=250,resizable=no,scrollbars=yes')"><img--%>
                                        <%--src="images/buy_cn.gif" border=0></a></td>--%>
                            <%--</tr>--%>
                        <%--</table>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
            <%--<P align=center><STRONG><FONT size=4>详细资料</FONT></STRONG></P>--%>
            <%--<HR align=center width="100%" color=#000000 noShade SIZE=1>--%>

            <%--<P align=center><IMG--%>
                    <%--style="BORDER-LEFT-COLOR: #000000; BORDER-BOTTOM-COLOR: #000000; WIDTH: 232px; BORDER-TOP-COLOR: #000000; POSITION: static; HEIGHT: 172px; BORDER-RIGHT-COLOR: #000000; "--%>
                    <%--height=294 alt="" hspace=0 src="images/${resfood.fphoto}" width=350--%>
                    <%--border=0></P>--%>
            <%--<P align=center><FONT size=3>${resfood.detail}</FONT></P>--%>
            <%--<br>--%>
        <%--</td>--%>

<%--<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>--%>
    <%--<TBODY>--%>
    <%--<TR>--%>
        <%--<TD background="images/005.gif" height=8></TD>--%>
    <%--</TR>--%>
    <%--</TBODY>--%>
</TABLE>
<%@include file="bottom.jsp"%>






