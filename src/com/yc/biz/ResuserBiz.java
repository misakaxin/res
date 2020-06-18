package com.yc.biz;

import com.yc.bean.Resuser;

import java.sql.SQLException;

public interface ResuserBiz {
    public boolean isUsernameExists(Resuser resuser) throws Exception;

    public boolean reg(Resuser resuser) throws SQLException;

    public Resuser login(Resuser resuser) throws Exception;

    public Resuser findResuser(Resuser resuser) throws Exception;
}
