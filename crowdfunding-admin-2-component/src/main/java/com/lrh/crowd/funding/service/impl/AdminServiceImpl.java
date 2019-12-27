package com.lrh.crowd.funding.service.impl;

import com.lrh.crowd.funding.entity.Admin;
import com.lrh.crowd.funding.entity.AdminExample;
import com.lrh.crowd.funding.mapper.AdminMapper;
import com.lrh.crowd.funding.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2019/12/27 13:57
 */

@Service
public class AdminServiceImpl  implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
