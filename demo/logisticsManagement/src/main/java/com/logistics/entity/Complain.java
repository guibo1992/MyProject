package com.logistics.entity;

import java.util.Date;

public class Complain {

    private Integer id;

    private String note;

    private Date tsTime;

    private String tsName;

    private String tsTel;

    private String tsCount;

    private String tsIdear;

    private String slName;

    private Date slTime;

    private Integer slState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getTsTime() {
        return tsTime;
    }

    public void setTsTime(Date tsTime) {
        this.tsTime = tsTime;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName == null ? null : tsName.trim();
    }

    public String getTsTel() {
        return tsTel;
    }

    public void setTsTel(String tsTel) {
        this.tsTel = tsTel == null ? null : tsTel.trim();
    }

    public String getTsCount() {
        return tsCount;
    }

    public void setTsCount(String tsCount) {
        this.tsCount = tsCount == null ? null : tsCount.trim();
    }

    public String getTsIdear() {
        return tsIdear;
    }

    public void setTsIdear(String tsIdear) {
        this.tsIdear = tsIdear == null ? null : tsIdear.trim();
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName == null ? null : slName.trim();
    }

    public Date getSlTime() {
        return slTime;
    }

    public void setSlTime(Date slTime) {
        this.slTime = slTime;
    }

    public Integer getSlState() {
        return slState;
    }

    public void setSlState(Integer slState) {
        this.slState = slState;
    }
}