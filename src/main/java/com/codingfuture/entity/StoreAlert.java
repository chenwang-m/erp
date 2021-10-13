package com.codingfuture.entity;

import java.io.Serializable;

public class StoreAlert implements Serializable {

    private Long uuid;
    private String name;
    private Long storenum;
    private Long outnum;

    public StoreAlert(String name, Long storenum, Long outnum) {
        this.name = name;
        this.storenum = storenum;
        this.outnum = outnum;
    }

    public StoreAlert() {
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStorenum() {
        return storenum;
    }

    public void setStorenum(Long storenum) {
        this.storenum = storenum;
    }

    public Long getOutnum() {
        return outnum;
    }

    public void setOutnum(Long outnum) {
        this.outnum = outnum;
    }
}
