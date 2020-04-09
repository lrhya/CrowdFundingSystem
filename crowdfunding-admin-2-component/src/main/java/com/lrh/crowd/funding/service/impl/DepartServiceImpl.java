package com.lrh.crowd.funding.service.impl;


import com.lrh.crowd.funding.entity.Depart;
import com.lrh.crowd.funding.entity.DepartExample;
import com.lrh.crowd.funding.mapper.DepartMapper;
import com.lrh.crowd.funding.service.api.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/12 14:29
 */
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    @Override
    public List<Depart> getAll() {
        return departMapper.selectByExample(new DepartExample());
    }

    @Override
    public void saveDepart(Depart depart) {
        departMapper.insert(depart);
    }

    @Override
    public Depart getDepartById(Integer departId) {
        return departMapper.selectByPrimaryKey(departId);
    }

    @Override
    public void updateDepart(Depart depart) {
        departMapper.updateByPrimaryKey(depart);
    }

    @Override
    public void removeDepart(Integer departId) {
        departMapper.deleteByPrimaryKey(departId);
    }
}


