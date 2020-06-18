package com.yc.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/webInit.do" ,initParams={
        @WebInitParam(name="webName" ,value="网上订餐"),
        @WebInitParam(name="copyright",value="cx"),
        @WebInitParam(name="keyword",value="这是一个神奇的网站，。。"),
        @WebInitParam(name="description",value="超绝可爱赖美云")},loadOnStartup=1)

public class WebInitServlet extends HttpServlet {
    private String webName;
    private String copyright;
    private String keyword;
    private String description;


    public WebInitServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        webName=config.getInitParameter("webName");
        copyright=config.getInitParameter("copyright");
        keyword=config.getInitParameter("keyword");
        description=config.getInitParameter("description");

        ServletContext application= config.getServletContext();
        application.setAttribute("webName",webName);
        application.setAttribute("copyright",copyright);
        application.setAttribute("keyword",keyword);
        application.setAttribute("description",description);

    }
}
