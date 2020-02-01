package com.lrh.crowd.funding.service.api;

import com.lrh.crowd.funding.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/1 15:39
 */
public interface AuthService {
    List<Auth> getAllAuth();

    List<Integer> getAssignedAuthIdList(Integer roleId);

    void updateRelationShipBetweenRoleAndAuth(Map<String, List<Integer>> assignDataMap);
}
