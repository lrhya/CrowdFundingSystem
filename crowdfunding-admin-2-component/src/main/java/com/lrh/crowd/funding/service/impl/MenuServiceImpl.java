package com.lrh.crowd.funding.service.impl;

import com.lrh.crowd.funding.entity.Menu;
import com.lrh.crowd.funding.entity.MenuExample;
import com.lrh.crowd.funding.mapper.MenuMapper;
import com.lrh.crowd.funding.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/31 13:25
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }
}
