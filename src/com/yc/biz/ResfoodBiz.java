package com.yc.biz;

import com.yc.bean.PageBean;
import com.yc.bean.Resfood;

import java.sql.SQLException;

public interface ResfoodBiz {
    public PageBean<Resfood> find(int pageno,int pagesize,String keyword) throws Exception;
    public Resfood findByItem(Resfood rf) throws Exception;

}
