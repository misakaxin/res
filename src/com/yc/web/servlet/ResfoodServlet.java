package com.yc.web.servlet;

import com.yc.bean.CartItem;
import com.yc.bean.PageBean;
import com.yc.bean.Resfood;
import com.yc.bean.Resuser;
import com.yc.biz.ResfoodBiz;
import com.yc.biz.ResfoodBizImpl;
import com.yc.biz.ResuserBiz;
import com.yc.biz.ResuserBizImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作
 * 1。首页分页
 * 2。查看历史
 * 3。查看点赞
 * 4。点赞
 * 5。发评论
 */

@WebServlet("/resfood.do")
public class ResfoodServlet extends BaseServlet {

    private  ResfoodBiz rb=new ResfoodBizImpl();
    private ResuserBiz userBiz=new ResuserBizImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("op");
        try{
            if("find".equals(op)) {
                findOp(request,response);
            }else if ("history".equals(op)){
                historyOp(request,response);
            }else if ("praise".equals(op)){
                praiseOp(request,response);
            }else if ("detail".equals(op)){
                detailOp(request,response);
            }else if ("buy".equals(op)){
                buyOp(request,response);
            }else if ("clearCart".equals(op)){
                clearCartOp(request,response);
            }else if ("makeOrder".equals(op)){
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

    private void makeOrderOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session=request.getSession();
        if (session.getAttribute("cart")==null|| ((Map<Integer,CartItem>)session.getAttribute("cart")).size()<=0 ){
            session.setAttribute("msg","购物车为空...");
            response.sendRedirect("showCart.jsp");
            return;

        }
        if (session.getAttribute("resuser")==null){
            session.setAttribute("msg","用户没有登陆...");
            response.sendRedirect("showCart.jsp");
            return;
        }
        Resuser user=(Resuser) session.getAttribute("resuser");
        userBiz.findResuser(user);

        session.setAttribute("resuser",user);

        request.getRequestDispatcher("/WEB-INF/userback/checkOut.jsp").forward(request,response);
    }

    private void clearCartOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        session.removeAttribute("cart");
        session.removeAttribute("total");

        Map<String,Integer> map=new HashMap<>();
        map.put("code",1);

        super.writeJson(map,response);
    }



    private void buyOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Resfood rf=super.parseMap(request, Resfood.class);
        rf=rb.findByItem(rf);
        int num=1;

        try {
                if (request.getParameter("num")!=null){
                    num=Integer.parseInt(request.getParameter("num"));
                }

            }catch (Exception ex){
                ex.printStackTrace();
                num=1;
        }
        //取出session
        HttpSession session=request.getSession();

        session.removeAttribute("msg");
        //判断在session中原来是否有购物车
        Map<Integer,CartItem<Resfood>> cart=new HashMap<>();
        if (session.getAttribute("cart")!=null){
            cart=(Map<Integer, CartItem<Resfood>>) session.getAttribute("cart");
        }
        //计算 map中的商品
        CartItem<Resfood> cartItem=null;
        if (cart.containsKey(rf.getFid())){
            //原来已经买过这个商品了
            cartItem=cart.get(rf.getFid());
            cartItem.setNum(cartItem.getNum()+num);
            if (cartItem.getNum()<=0) {
                cart.remove(rf.getFid());
            }

        }else {
            if (num > 0) {
                cartItem=new CartItem<>();
                cartItem.setNum(num);
                cartItem.setT(rf);
            }
        }
        if (cartItem.getNum()>0) {
            cart.put(rf.getFid(), cartItem);
        }

        double total=0.0;

        for (Map.Entry<Integer,CartItem<Resfood>> entry:cart.entrySet()){
            CartItem<Resfood> ci=entry.getValue();
            double smallCount=ci.getT().getRealprice()*ci.getNum();
            ci.setSmallCount(smallCount);

            total+=smallCount;

        }
        session.setAttribute("total",total);
        session.setAttribute("cart",cart);

        String isAjax=request.getParameter("isAjax");
        if ("1".equals(isAjax)){
            Map<String,String> map=new HashMap<>();
            map.put("code",1+"");
            map.put("total",total+"");
            map.put("fid",rf.getFid()+"");
            map.put("num",cartItem.getNum()+"");
            map.put("smallCount",cartItem.getSmallCount()+"");
            super.writeJson(map,response);

        }else {
            response.sendRedirect("showCart.jsp");
        }

    }

    private void detailOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Resfood rf=super.parseMap(request,Resfood.class);

        //方案一:利用session来存pageBean ，再根据id到pageBean查是否有这个商品,优点:不访问数据库
        //request.getSession().getAttribute("pageBean"); ->getList() ->for循环
        //缺点:

        //方案一:根据rf中到fid再到数据库查一次
        rf=rb.findByItem(rf);
        request.setAttribute("resfood",rf);
        request.getRequestDispatcher("detail.jsp").forward(request,response);
    }



    private void praiseOp(HttpServletRequest request, HttpServletResponse response) {

    }


    private void historyOp(HttpServletRequest request,HttpServletResponse response){

    }

    private void findOp(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session=request.getSession();
        int pageno=1;
        if (request.getParameter("pageno")!=null && !"".equals(request.getParameter("pageno"))){
            pageno=Integer.parseInt(request.getParameter("pageno")  );
        }
        int pagesize=10;
        if (request.getParameter("pagesize")!=null){
            pagesize=Integer.parseInt(request.getParameter("pagesize") );
        }
        String keyword=null;
        if (request.getParameter("searchWords")!=null){
            keyword=request.getParameter("searchWords");
            session.setAttribute("searchWords",keyword);
        }


        try {
            PageBean<Resfood> pb=rb.find(pageno,pagesize,keyword);
            request.setAttribute("pageBean" ,pb);
//            session.setAttribute("pageBean",pb);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        request.getRequestDispatcher("show.jsp").forward(request,response);
    }

}
