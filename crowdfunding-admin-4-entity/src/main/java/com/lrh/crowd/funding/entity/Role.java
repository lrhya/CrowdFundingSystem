package com.lrh.crowd.funding.entity;

public class Role {
    private Integer tId;

    private String tName;

    public Role() {
    }

    public Role(Integer tId, String tName) {
        this.tId = tId;
        this.tName = tName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                '}';
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }
}