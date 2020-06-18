package com.yc.biz;

import com.yc.bean.CartItem;
import com.yc.bean.Resfood;
import com.yc.bean.Resuser;
import com.yc.dao.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderBizImpl implements OrderBiz {
    private DbHelper db=new DbHelper();


    @Override
    public void makeOrder(Resuser resuser, Map<Integer, CartItem<Resfood>> cart) throws SQLException {
        Connection con=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;

        con=db.getConn();
        String sql=null;
        try{

            //加入 开始事务
            con.setAutoCommit(false);

            //更新用户地址
            sql="update resuser set address=?,telephone=? where userid=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,resuser.getAddress());
            pstm.setString(2,resuser.getTelephone());
            pstm.setInt(3,resuser.getUserid());
            pstm.executeUpdate();

            //插入订单
            sql="insert into resorder(userid,address,tel,ordertime,deliverytime,status) values (?,?,?,sysdate(),sysdate(),0)";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,resuser.getUserid());
            pstm.setString(2,resuser.getAddress());
            pstm.setString(3,resuser.getTelephone());
            pstm.executeUpdate();

            int roid=0;
            sql="select roid from resorder order by roid desc limit 0,1";
            pstm=con.prepareStatement(sql);
            rs=pstm.executeQuery();
            if (rs.next()){
                roid=rs.getInt(1);

            }

            //插入订单项
            for (Map.Entry<Integer,CartItem<Resfood>> entry:cart.entrySet()
                 ) {
                int foodid=entry.getKey();
                CartItem<Resfood> ci=entry.getValue();

                sql="insert into resorderitem(roid, fid, dealprice, num) value (?,?,?,?)";
                pstm=con.prepareStatement(sql);
                pstm.setInt(1,roid);
                pstm.setInt(2, foodid);
                pstm.setDouble(3,ci.getT().getRealprice());
                pstm.setInt(4,ci.getNum());
                pstm.executeUpdate();
            }

            con.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (con!=null){
                con.rollback();
            }
        }finally {
            con.setAutoCommit(true);//恢复原
            pstm.close();
            rs.close();
            con.close();
        }

    }
}
