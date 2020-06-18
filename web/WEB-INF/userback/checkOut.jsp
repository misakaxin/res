<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../header.jsp" %>
<td>
<form action="back/order.do">
    <input type="hidden" name="userid" value="${resuser.userid}" />
    <input type="hidden" name="op" value="makeOrder" />


    <table cellspacing=1 cellpadding=4 width="92%" border=0
       align="CENTER" bgcolor="#c0c0c0">
    <tr bgcolor="#dadada">
        <td colspan="5" height="25" align=center><font color="#000000">请确认支付和配送信息</font></td>
    </tr>

    <tr bgcolor="#ffffff">
        <td width="22%" align="RIGHT"><font color="#000000">订 餐
            人：</font></td>
        <td colspan=4 width="78%"><input type="text" name="name"
                                         size="46" maxlength="20" value="${resuser.username }"></td>
    </tr>

    <tr bgcolor="#ffffff">
        <td width="22%" align="RIGHT"><font color="#000000">送货地址：</font></td>
        <td colspan=4 width="78%"><input type="text" name="address"
                                         size="46" maxlength="200" value="${resuser.address}"></td>
    </tr>

    <tr bgcolor="#ffffff">
        <td width="22%" height="31" align="RIGHT"><font
                color="#000000">联系电话：</font></td>
        <td colspan=4 width="78%" height="31"><input type="text"
                                                     name="telephone" size="46" maxlength="13" value="${resuser.telephone}"></td>
    </tr>

    <tr bgcolor="#dadada">
        <td colspan="5" height="12" align="center"><input type=submit
                                                          value="确认以上信息无误,提交" name=Submit></td>
    </tr>
</table>
</form>
</td>

<%@include file="../../bottom.jsp" %>

