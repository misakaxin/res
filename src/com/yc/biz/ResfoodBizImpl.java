package com.yc.biz;

import com.yc.bean.PageBean;
import com.yc.bean.Resfood;
import com.yc.dao.DbHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResfoodBizImpl implements ResfoodBiz {


    private DbHelper db=new DbHelper();

    private long findCount(String keyword) throws SQLException{
        double result=0;
        String sql=" select count(fid) from resfood where 1=1  ";
        if (keyword!=null && !"".equals(keyword)){
            sql+=" and fname like ? or detail like ? ";
            result=db.getCount(sql,keyword,keyword);
        }else {
            result=db.getCount(sql);
        }
        return (long)result;
    }
    private List<Resfood> search(Integer pageno,Integer pagesize,String keyword)throws Exception{
        String sql=" select fid,fname,normprice,realprice,fphoto from resfood where 1=1";
        if (keyword!=null && !"".equals(keyword)){
            sql+=" and fname like ? or detail like ? ";
        }
        sql+=" order by fid desc ";
        if (pageno!=null &&pageno>0){
            int start=(pageno-1)*pagesize;
            sql+=" limit "+start+","+pagesize;
        }

        if (keyword!=null && !"".equals(keyword)){
            return db.findMutil(sql,Resfood.class,keyword,keyword);
        }else {
            return db.findMutil(sql,Resfood.class);
        }
    }
    @Override
    public PageBean<Resfood> find(int pageno, int pagesize, String keyword) throws Exception {
        if (keyword!=null && !"".equals(keyword)){
            keyword="%"+keyword+"%";
        }
        long count=findCount(keyword);
        List<Resfood> list=search(pageno,pagesize,keyword);
        PageBean pb=new PageBean();
        pb.setList(list);
        pb.setTotal(count);
        pb.setPageno(pageno);
        pb.setPagesize(pagesize);
        return pb;
    }

    @Override
    public Resfood findByItem(Resfood rf) throws Exception {
        List<Object> params=new ArrayList<>();
        String sql="select fid,fname,normprice,realprice,fphoto,detail from resfood where 1=1 ";
        if (rf!=null){
            if (rf.getFid()!=null && rf.getFid()>0){
                sql+=" and fid=? ";
                params.add(rf.getFid());
            }
        }
        List<Resfood> list=db.findMutil(sql, Resfood.class, params.toArray());
        return list!=null&&list.size()>0?list.get(0):null;
    }

    public static void main(String[] args) throws Exception {
        ResfoodBiz rb=new ResfoodBizImpl();
        PageBean<Resfood> pb=rb.find(1, 10, null);
        System.out.println(pb);
    }
}
