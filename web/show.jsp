<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>

            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                <TR>
                    <TD><IMG height=72 src="images/001.jpg" width=595></TD>
                </TR>
                </TBODY>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                <TR>
                    <TD width="90%">
                        <div class='cnt'>
                            <marquee
                                    style="FONT-SIZE: 13px; COLOR: #0000FF" scrollamount='5'
                                    direction='left'><IMG height=15 src="images/tp009.gif"
                                                          width=15> 欢迎您使用我学我会网上订餐系统，祝您用餐愉快！
                            </marquee>
                        </div>
                        &nbsp;&nbsp;
                    </TD>
                </TR>
                </TBODY>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                <TBODY>
                <TR>
                    <TD align=right background="images/004.gif" height=19></TD>
                </TR>
                </TBODY>
            </TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width="96%" align=center border=0>
                <TBODY>
                <TR>
                    <TD>

                        <TABLE cellSpacing=1 cellPadding=1 width="100%" align=center
                               bgColor=#c0c0c0 border=0>
                            <TBODY>
                            <TR bgColor=#dadada>

                                <TD width="100%" align="right"><a href="showCart.jsp"><img
                                        src="images/lcart_cn.gif" border=0></a></TD>
                            </TR>
                            </TBODY>
                        </TABLE>
                        <BR>

                        <form name="myform" action="resfood.do" method="post">
                            <input type="hidden" name="op" value="find" />

                            <input type="text" name="searchWords" value="${searchWords }"/><input type="submit" value="搜索"/>
                            <hr />


                        <TABLE cellSpacing=2 cellPadding=1 width="100%" align=center
                               border=0>
                            <TBODY>


                            <c:forEach items="${pageBean.list }" var="resfood" varStatus="vs">
                                <c:if test="${vs.index%2==0}">
                                    <TR>
                                </c:if>
                                <TD>
                                    <TABLE height="100%" cellSpacing=1 cellPadding=2 border=0>
                                        <TBODY>
                                        <TR>
                                            <TD vAlign=top width=90 height=90><A href=#
                                                                                 target=_blank><IMG height=80
                                                                                                    alt=点击图片查看内容
                                                                                                    src="images/${resfood.fphoto}"
                                                                                                    width=80
                                                                                                    border=0></A></TD>
                                            <TD vAlign=top>
                                                <TABLE cellSpacing=1 cellPadding=0 width="100%" align=center
                                                       border=0>
                                                    <TBODY>
                                                    <TR>
                                                        <TD><A href=# target=_blank><STRONG>${resfood.fname}</STRONG></A></TD>
                                                    </TR>
                                                    <TR>
                                                        <TD height=21>
                                                            <FONT >原价：${resfood.normprice}元</FONT><BR>
                                                            <FONT color=#ff0000>跳楼价：${resfood.realpriceString}元</FONT><BR>
                                                        </TD>
                                                    </TR>
                                                    </TBODY>
                                                </TABLE>
                                            </TD>
                                        </TR>
                                        <TR>
                                            <TD height=28>编号: ${resfood.fid}</TD>
                                            <TD>
                                                <TABLE cellSpacing=1 cellPadding=0 width=145 border=0>
                                                    <TBODY>
                                                    <TR>
                                                        <TD align="middle" width="70"><a href="resfood.do?op=buy&fid=${resfood.fid}"><img
                                                                src="images/buy_cn.gif" border="0"
                                                                longdesc="shoppingCart.html"></a></TD>
                                                        <TD align=middle width=70><A href="resfood.do?op=detail&fid=${resfood.fid}"
                                                        target="_blank"><IMG
                                                                src="images/detail_cn.gif" border=0></A></TD>
                                                    </TR>
                                                    </TBODY>
                                                </TABLE>
                                            </TD>
                                        </TR>
                                        </TBODY>
                                    </TABLE>
                                </TD>

                            <c:if test="${vs.index%2==1}">
                                    </TR>
                                </c:if>

                            </c:forEach>

                            </TBODY>
                        </TABLE>
                    </TD>
                </TR>
                </TBODY>
            </TABLE>

            <hr/>

            <div id="pageBar">
            <a href="javascript:void" onclick="goPage(1)">第一页</a>
            <a href="javascript:void" onclick="goPage(${pageBean.prepage}) ">上一页</a>
            <a href="javascript:void" onclick="goPage(${pageBean.nextpage})">下一页</a>
            <a href="javascript:void" onclick="goPage(${pageBean.totalpages})">最后一页</a>
            <hr/>
            ${pageBean.pageno}页/共${pageBean.totalpages}页 每页${pageBean.pagesize}条/共${pageBean.total}条
            到<input id="pageno" type="text" name="pageno"/>页
            </div>
            </form>

<script type="text/javascript">
    function goPage( pageno ) {
        if (pageno !=null &&""!=pageno){
            document.getElementById("pageno").value=pageno;
        }
        document.myform.submit();
    }
</script>


<%@include file="bottom.jsp" %>
