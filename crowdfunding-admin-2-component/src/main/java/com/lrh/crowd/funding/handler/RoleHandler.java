package com.lrh.crowd.funding.handler;

import com.github.pagehelper.PageInfo;
import com.lrh.crowd.funding.entity.ResultEntity;
import com.lrh.crowd.funding.entity.Role;
import com.lrh.crowd.funding.service.api.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/9 9:55
 */
@Controller
public class RoleHandler {

    @Autowired
   private RoleService roleService;

    @ResponseBody
    @RequestMapping("/role/search/by/keyword")
    public ResultEntity<PageInfo<Role>> search(
            @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
            @RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
            @RequestParam(value="keyword", defaultValue="") String keyword
    ) {

        // 1.查询得到PageInfo对象
        PageInfo<Role> pageInfo = roleService.queryForKeywordWithPage(pageNum, pageSize, keyword);

        // 2.封装结果对象返回
        return ResultEntity.successWithData(pageInfo);
    }

}
