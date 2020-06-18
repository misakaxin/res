<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<td>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
       bgcolor="#FFFFFF" height="100%">
    <tr valign="top">
        <td>
            <table width="98%" border="0" cellspacing="1" cellpadding="2"
                   align="center">
                <tr valign="bottom">
                    <td height="30"><img
                            src="http://i-560.wz8.cn/pic1/shop/cart.gif"> <font
                            color="#000000">您的购物车中有以下商品</font></td>
                </tr>
            </table>
            <table width="98%" border="0" cellspacing="2" cellpadding="0"
                   align="center">
                <tr bgcolor=#808000>
                    <td height="1" bgcolor="#999999"></td>
                </tr>
            </table>
            <table width="98%" border="0" cellspacing="2" cellpadding="0"
                   align="center">
                <tr>
                    <td height="5"></td>
                </tr>
            </table>
            <table width="98%" border="0" cellspacing="0" cellpadding="0"
                   align="center">
                <tr>
                    <td>
                        <table width="100%" border="0" align="CENTER" cellpadding="2"
                               cellspacing="1" bgcolor="#c0c0c0">

                            <thead>
                            <tr bgcolor="#dadada">
                                <td height="22" width="50">
                                    <div align="CENTER"><font color="#000000">编号</font></div>
                                </td>
                                <td width="610" height="22">
                                    <div align="CENTER"><font color="#000000">商品名称</font></div>
                                </td>
                                <td height="22" width="104">
                                    <div align="CENTER"><font color="#000000">单价</font></div>
                                </td>
                                <td height="22" width="100">
                                    <div align="CENTER"><font color="#000000">数量</font></div>
                                </td>
                                <td width="116" height="22">
                                    <div align="CENTER"><font color="#000000">金额</font></div>
                                </td>
                            </tr>
                            </thead>
                     <tbody id="cartItemDiv">
                     <c:forEach items="${cart}" var="entry" >

                            <tr bgcolor="#ffffff" id="tr_${entry.key}">
                                <td width="50" align="center" height="22"><font
                                        color="#000000">${entry.key}</font></td>
                                <td align="center" height="22"><font color="#000000">${entry.value.t.fname}</font>
                                    <input type="hidden" name="fid" value="${entry.value.t.fid}"></td>
                                <td width="104" align="center" height="22"><font
                                        color="#000000">${entry.value.t.realpriceString}</font></td>
                                <td width="100" class="hh" align="center" height="22">
                                    <a href="javascript:void" onclick="changeNum(-1,${entry.key})">-</a>
                                        <span id="num_${entry.key}">
                                        ${entry.value.num}
                                        </span>
                                    <a href="javascript:void" onclick="changeNum(1,${entry.key})">+</a>
                                </td>
                                <td width="116" class="bb" align="center" height="22"><font
                                        color="#000000" id="smallCount_${entry.key}">￥${entry.value.smallCount}</font></td>
                            </tr>
                         </c:forEach>
                     </tbody>

                            <tr bgcolor="#dadada">
                                <td width="50" height="22" align="center"><font
                                        color="#000000">合计</font></td>
                                <td height="22" align="center"><font color="#000000">-</font></td>
                                <td width="104" height="22" align="center"><font
                                        color="#000000">-</font></td>
                                <td width="100" class="hh" height="22" align="center"><font
                                        color="#000000">-</font></td>
                                <td width="116" class="bb" align="center" height="22"><font
                                        color="#000000" id="total">￥${total} </font></td>
                            </tr>


                        </table>
                        <br>

                        <table width="300" border="0" cellspacing="1" cellpadding="4"
                               align="CENTER" bgcolor="#c0c0c0">
                            <tr bgcolor="#dadada">
                                <td height="10" align="center"><a href="javascript:void " onclick="clearCart()"><font
                                        color="#000000">清空购物车</font></a></td>
                                <td height="10" align="center" style="cursor:hand" onClick="location.href='index.jsp'"><font
                                        color="#000000">继续购物</font></td>
                                <td height="10" align="center" style="cursor:hand" onClick="location.href='resfood.do?op=makeOrder'"><font
                                        color="#000000">生成订单</font></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
    ${msg }
    <script type="text/javascript">
        function clearCart() {
            $.ajax({
                url:"resfood.do?op=clearCart",
                type:"post",
                dataType:"json",
                success:function (data) {
                    if (data.code==1){
                        $("#cartItemDiv").html('');
                        $("#total").html('¥0.0');

                    }
                },
                error:function (request,error) {
                    alert(error);
                }
            });
        }
        function changeNum(num,fid) {
            $.ajax({
                url:"resfood.do?op=buy",
                type:"post",
                data:"num="+num+"&fid="+fid+"&isAjax=1",
                dataType:"json",
                success:function (data) {

                    if (data.code==1){
                        if( data.num<=0){
                            $("#tr_"+data.fid).remove();
                        }else {
                            $("#num_"+data.fid ).html( data.num);
                            $("#smallCount_"+data.fid ).html( data.smallCount);
                        }
                        $("#total").html( data.total);
                    }
                },
                error:function (request,error) {
                    alert(error);
                }
            });
        }
    </script>
</td>

<%@include file="bottom.jsp" %>
