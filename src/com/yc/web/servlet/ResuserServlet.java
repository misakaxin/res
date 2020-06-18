package com.yc.web.servlet;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import com.yc.biz.ResuserBizImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/resuser.do")
public class ResuserServlet extends BaseServlet {

    private ResuserBiz resuserBiz=new ResuserBizImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("op");
        try{
            if("toReg".equals(op)) {
                toRegOp(request,response);
            }else if ("reg".equals(op)){
                regOp(request,response);
            }else if ("checkUsername".equals(op)){
                checkUsernameOp(request,response);
            }else if ("login".equals(op)){
                loginOp(request,response);
            }else if ("isUserLogin".equals(op)){
                isUserLoginOp(request,response);
            }else if ("logout".equals(op)){
                logoutOp(request,response);
            }
            else {
                go404(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            go500(request,response,e);
        }
    }

    private void logoutOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        session.removeAttribute("resuser");
        response.sendRedirect("loginForm.jsp");
    }

    private void isUserLoginOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();

        if (session.getAttribute("resuser")==null){
            response.sendRedirect("loginForm.jsp");
        }else {
            request.getRequestDispatcher("/WEB-INF/front/userMan.jsp").forward(request,response);
        }
    }

    private void loginOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Resuser resuser=super.parseMap(request, Resuser.class);
        Resuser result=resuserBiz.login(resuser);
        HttpSession session=request.getSession();

        if (result==null){
            session.setAttribute("msg","登陆失败");
            response.sendRedirect("loginForm.jsp");
        }else {

            session.setAttribute("resuser",result);
            //因为userMan.jsp页面在WEB-INF下，所以只能转发.
            request.getRequestDispatcher("/WEB-INF/front/userMan.jsp").forward(request,response);
        }
    }

    private void checkUsernameOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Resuser resuser=super.parseMap(request, Resuser.class);
        boolean b=resuserBiz.isUsernameExists(resuser);
        Map<String, String> map=new HashMap<>();
        map.put("code",1+"");
        if (b) {
            map.put("msg","用户名存在");
        }else {
            map.put("msg","用户名可以使用");

        }
        super.writeJson(map,response);
    }

    private void regOp(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, InstantiationException, IOException, SQLException {
        Resuser resuser=super.parseMap(request, Resuser.class);

        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();


        String valicode=request.getParameter("valicode");

        String key=(String)session.getAttribute("key");

        if (!key.equals(valicode)){
            out.println("<script> alert('验证码错误。。。');location.href='reg.jsp';</script>");
            return;
        }

        boolean r=resuserBiz.reg(resuser);
        if ( r ){
            session.setAttribute("msg","注册成功");
            response.sendRedirect("login.jsp");

        }else {
            session.setAttribute("msg","注册失败");
            response.sendRedirect("resuser.do?op=toReg");
        }


    }

    private void toRegOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getHeader("user-agent"))
        request.getRequestDispatcher("reg.jsp").forward(request,response);
    }
}
