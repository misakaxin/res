package com.yc.bean;

public class Resorderitem {
    private Integer roiid ;
    private Integer roid  ;
    private Integer  fid  ;
    private Double dealprice ;
    private Integer  num;


    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid=fid;
    }

    public Double getDealprice() {
        return dealprice;
    }

    public void setDealprice(Double dealprice) {
        this.dealprice=dealprice;
    }

    public Integer getRoiid() {
        return roiid;
    }

    public void setRoiid(Integer roiid) {
        this.roiid=roiid;
    }

    public Integer getRoid() {
        return roid;
    }

    public void setRoid(Integer roid) {
        this.roid=roid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num=num;
    }

    @Override
    public String toString() {
        return "Resorderitem{" +
               "roiid=" + roiid +
               ", roid=" + roid +
               ", fid=" + fid +
               ", dealprice=" + dealprice +
               ", num=" + num +
               '}';
    }
}
