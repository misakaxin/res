package com.yc.bean;

import java.io.Serializable;

public class CartItem<T> implements Serializable {
    private T t;
    private Integer num;
    private Double smallCount;

    public Double getSmallCount() {
        return smallCount;
    }

    public void setSmallCount(Double smallCount) {
        this.smallCount=smallCount;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t=t;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num=num;
    }

    @Override
    public String toString() {
        return "CartItem{" +
               "t=" + t +
               ", num=" + num +
               ", smallCount=" + smallCount +
               '}';
    }
}

