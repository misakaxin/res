package com.yc.biz;

import com.yc.bean.CartItem;
import com.yc.bean.Resfood;
import com.yc.bean.Resuser;

import java.sql.SQLException;
import java.util.Map;

public interface OrderBiz {
    public void makeOrder(Resuser resuser, Map<Integer,CartItem<Resfood>> cart) throws SQLException;
}
