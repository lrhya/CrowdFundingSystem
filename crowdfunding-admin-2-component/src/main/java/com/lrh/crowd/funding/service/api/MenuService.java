package com.lrh.crowd.funding.service.api;

import com.lrh.crowd.funding.entity.Menu;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/31 13:22
 */
public interface MenuService {
    List<Menu> getAll();

    void saveMenu(Menu menu);

    Menu getMenuById(Integer menuId);

    void updateMenu(Menu menu);

    void removeMenu(Integer menuId);
}
