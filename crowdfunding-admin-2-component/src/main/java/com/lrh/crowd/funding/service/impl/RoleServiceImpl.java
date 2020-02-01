package com.lrh.crowd.funding.service.impl;


import com.lrh.crowd.funding.CrowdFundingUtils;
import com.lrh.crowd.funding.entity.Role;
import com.lrh.crowd.funding.entity.RoleExample;
import com.lrh.crowd.funding.mapper.RoleMapper;
import com.lrh.crowd.funding.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/9 9:54
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;




    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }



    @Override
    public void saveRole(String roleName) {
        roleMapper.insert(new Role(null, roleName));
    }

    @Override
    public void batchRemove(List<Integer> roleIdList) {

        RoleExample roleExample = new RoleExample();

        roleExample.createCriteria().andTIdIn(roleIdList);

        roleMapper.deleteByExample(roleExample);
    }



    @Override
    public List<Role> getRoleListByIdList(List<Integer> roleIdList) {

        // 预期的SQL语句
        // select id,name from t_role where id in (1,2,3,6,12)

        // 创建实体类Role对应的Example对象
        RoleExample roleExample = new RoleExample();

        // 在Example对象中封装查询条件
        roleExample.createCriteria().andTIdIn(roleIdList);

        // 执行查询
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public PageInfo<Role> queryForKeywordWithPage(
            Integer pageNum,
            Integer pageSize,
            String keyword) {

        // 1.开启分页功能
        PageHelper.startPage(pageNum, pageSize);

        // 2.执行查询
        List<Role> list = roleMapper.selectForKeywordSearch(keyword);

        // 3.封装为PageInfo对象
        return new PageInfo<>(list);
    }

    @Override
    public List<Role> getAssignedRoleList(Integer adminId) {

        List<Role> roleList = roleMapper.selectAssignedRoleList(adminId);

        return roleList;
    }

    @Override
    public List<Role> getUnAssignedRoleList(Integer adminId) {

        List<Role> roleList = roleMapper.selectUnAssignedRoleList(adminId);

        return roleList;
    }

    @Override
    public void updateRelationship(Integer adminId, List<Integer> roleIdList) {

        // 1.删除全部旧数据
        roleMapper.deleteOldAdminRelationship(adminId);

        // 2.保存全部新数据
        if(CrowdFundingUtils.collectionEffective(roleIdList)) {
            roleMapper.insertNewAdminRelationship(adminId, roleIdList);
        }

    }


}
