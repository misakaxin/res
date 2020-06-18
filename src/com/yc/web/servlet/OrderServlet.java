package com.yc.web.servlet;

import com.yc.bean.CartItem;
import com.yc.bean.Resfood;
import com.yc.bean.Resuser;
import com.yc.biz.OrderBiz;
import com.yc.biz.OrderBizImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/back/order.do")
public class OrderServlet extends BaseServlet {

    private OrderBiz obi=new OrderBizImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("op");
        try{
            if("makeOrder".equals(op)) {
                makeOrderOp(request,response);
            }
            else {
                go404(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            go500(request,response,e);
        }
    }

    private void makeOrderOp(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, IOException, ServletException {
        Resuser user=super.parseMap(request,Resuser.class);
        HttpSession session=request.getSession();
        Map<Integer,CartItem<Resfood>> cart=(Map<Integer, CartItem<Resfood>>) session.getAttribute("cart");
        obi.makeOrder(user,cart);

        session.removeAttribute("cart");
        session.removeAttribute("total");
        request.getRequestDispatcher("/WEB-INF/userback/seeYou.jsp").forward(request,response);

    }
}

