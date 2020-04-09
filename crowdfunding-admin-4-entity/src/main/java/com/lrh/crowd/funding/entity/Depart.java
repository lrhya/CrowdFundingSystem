package com.lrh.crowd.funding.entity;

import java.util.ArrayList;
import java.util.List;

public class Depart {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    // 当前节点的子节点集合，设置默认值是为了避免组装节点时空指针异常
    private List<Depart> children = new ArrayList<>();   //存储子节点

    // 控制节点展开还是折叠，设置为true是让整个树形菜单默认展开
    private Boolean open = true;


    public Depart() {
    }

    public Depart(Integer id, Integer pid, String name, String icon) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Depart{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", open=" + open +
                '}';
    }

    public List<Depart> getChildren() {
        return children;
    }

    public void setChildren(List<Depart> children) {
        this.children = children;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}