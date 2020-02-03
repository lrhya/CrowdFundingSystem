package com.lrh.crowd.funding.handler;

import com.github.pagehelper.PageInfo;
import com.lrh.crowd.funding.entity.ResultEntity;
import com.lrh.crowd.funding.entity.Role;


import com.lrh.crowd.funding.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @RequestMapping("/role/update/role")
    public ResultEntity<String> updateRole(Role role, HttpServletRequest request) {

        System.out.println("请求体中的值：" + request.getParameter("tId"));
        System.out.println("请求体中的值：" + request.getParameter("tName"));
        System.out.println("将要更新的员工数据：" + role);
        roleService.updateRole(role);

        return ResultEntity.successWithoutData();
    }




    @ResponseBody
    @RequestMapping("/role/save/role")
    public ResultEntity<String> saveRole(@RequestParam("roleName") String roleName) {

        roleService.saveRole(roleName);

        return ResultEntity.successWithoutData();
    }




    @ResponseBody
    @RequestMapping("/role/batch/remove")
    public ResultEntity<String> batchRemove(@RequestBody List<Integer> roleIdList) {

        roleService.batchRemove(roleIdList);

        return ResultEntity.successWithoutData();
    }


    @ResponseBody
    @RequestMapping("/role/get/list/by/id/list")
    public ResultEntity<List<Role>> getRoleListByIdList(@RequestBody List<Integer> roleIdList) {

        List<Role> roleList = roleService.getRoleListByIdList(roleIdList);

        return ResultEntity.successWithData(roleList);
    }

    // SpEL表达式
    @PreAuthorize("hasAuthority('role:get')")
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
