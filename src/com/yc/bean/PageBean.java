package com.yc.bean;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {
    private List<T> list;

    private int pageno;
    private int pagesize=0;
    private int prepage;
    private int nextpage;
    private long total;
    private int totalpages=1;


    public int getTotalpages(){
        totalpages=(int)(total%pagesize==0?total/pagesize:(total/pagesize+1));
        return totalpages;
    }

    public int getPrepage(){
        if (pageno>0){
            prepage=pageno-1;
        }else {
            prepage=1;
        }
        return prepage;
    }

    public int getNextpage() {
        int tp=getTotalpages();
        if (pageno==tp){
            nextpage=tp;
        }else {
            nextpage=pageno+1;
        }
        return nextpage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list=list;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pagepno) {
        this.pageno=pagepno;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize=pagesize;
    }

    public void setPrepage(int prepage) {
        this.prepage=prepage;
    }


    public void setNextpage(int nextpage) {
        this.nextpage=nextpage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total=total;
    }

    @Override
    public String toString() {
        return "PageBean{" +
               "list=" + list +
               ", pagepno=" + pageno +
               ", pagesize=" + pagesize +
               ", prepage=" + prepage +
               ", nextpage=" + nextpage +
               ", total=" + total +
               ", totalpages=" + totalpages +
               '}';
    }
}
