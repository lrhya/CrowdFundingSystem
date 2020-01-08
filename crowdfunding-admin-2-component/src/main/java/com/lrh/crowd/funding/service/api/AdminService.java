package com.lrh.crowd.funding.service.api;

import com.github.pagehelper.PageInfo;
import com.lrh.crowd.funding.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2019/12/27 13:56
 */

public interface AdminService {
    List<Admin> getAll();

    void updateAdmin();

    //登录验证
    Admin login(String loginAcct, String userPswd);

    //分页显示信息
    PageInfo<Admin> queryForKeywordSearch(Integer pageNum,Integer pageSize,String keyword);

    //批量删除
    void batchRemove(List<Integer> adminIdList);

    void saveAdmin(Admin admin);

    Admin getAdminById(Integer adminId);

    void updateAdmin(Admin admin);
}
