package com.yc.biz;

import com.yc.bean.Resuser;
import com.yc.dao.DbHelper;
import com.yc.utils.Encrypts;

import java.sql.SQLException;
import java.util.List;

public class ResuserBizImpl implements ResuserBiz {
    private DbHelper db=new DbHelper();

    @Override
    public boolean isUsernameExists(Resuser resuser) throws Exception {
        String sql="select * from resuser where username=? ";
        List<Resuser> list=db.findMutil(sql, Resuser.class, resuser.getUsername());
        return list==null||list.size()<0?false:true ;

    }

    @Override
    public boolean reg(Resuser resuser) throws SQLException {
        String pwd=Encrypts.md5AndSha(resuser.getPwd());

        String sql="insert into resuser (username,pwd,email) values(?,?,?) ";
        int r= db.update(sql,resuser.getUsername(),pwd,resuser.getEmail());

        if (r>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Resuser login(Resuser resuser) throws Exception {
        String sql="select * from resuser where username=? and pwd=? ";
        String pwd=Encrypts.md5AndSha(resuser.getPwd());
        List<Resuser> list=db.findMutil(sql, Resuser.class, resuser.getUsername(),pwd);
        return list==null||list.size()<0?null:list.get(0) ;
    }

    @Override
    public Resuser findResuser(Resuser resuser) throws Exception {
        String sql="select * from resuser where userid=? ";
        List<Resuser> list=db.findMutil(sql, Resuser.class, resuser.getUserid());
        return list==null||list.size()<0?null:list.get(0) ;

    }
}
