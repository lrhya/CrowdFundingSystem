package com.lrh.crowd.funding.service.api;

import com.github.pagehelper.PageInfo;
import com.lrh.crowd.funding.entity.Role;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/9 9:52
 */
public interface RoleService {

    PageInfo<Role> queryForKeywordWithPage(
            Integer pageNum,
            Integer pageSize,
            String keyword);
}
