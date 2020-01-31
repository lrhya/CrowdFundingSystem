package com.lrh.crowd.funding.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Integer id;//数据库表主键

    private Integer pid;//父节点ID，如果pid为null,则说明该结点为根节点

    private String name;//结点名

    private String url; //结点url地址

    private String icon; //结点的图标

    // 当前节点的子节点集合，设置默认值是为了避免组装节点时空指针异常
    private List<Menu> children = new ArrayList<>();   //存储子节点

    // 控制节点展开还是折叠，设置为true是让整个树形菜单默认展开
    private Boolean open = true;

    public Menu() {
    }

    public Menu(Integer id, Integer pid, String name, String url, String icon) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.icon = icon;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                '}';
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}