package com.lrh.crowd.funding.service.impl;

import com.lrh.crowd.funding.CrowdFundingUtils;
import com.lrh.crowd.funding.entity.Auth;
import com.lrh.crowd.funding.entity.AuthExample;
import com.lrh.crowd.funding.mapper.AuthMapper;
import com.lrh.crowd.funding.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/1 15:39
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthIdList(Integer roleId) {
        return authMapper.selectAssignedAuthIdList(roleId);
    }

    @Override
    public void updateRelationShipBetweenRoleAndAuth(Map<String, List<Integer>> assignDataMap) {

        // 1.获取两部分List数据
        List<Integer> roleIdList = assignDataMap.get("roleIdList");
        List<Integer> authIdList = assignDataMap.get("authIdList");

        // 2.取出roleId
        Integer roleId = roleIdList.get(0);

        // 3.删除旧数据
        authMapper.deleteOldRelationship(roleId);

        // 4.保存新数据
        if(CrowdFundingUtils.collectionEffective(authIdList)) {
            authMapper.insertNewRelationship(roleId, authIdList);
        }
    }


}
