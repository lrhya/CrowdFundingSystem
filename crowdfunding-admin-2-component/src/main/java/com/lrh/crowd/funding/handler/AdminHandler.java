package com.lrh.crowd.funding.handler;

import com.github.pagehelper.PageInfo;
import com.lrh.crowd.funding.CrowdFundingConstant;
import com.lrh.crowd.funding.entity.Admin;
import com.lrh.crowd.funding.entity.ResultEntity;
import com.lrh.crowd.funding.service.api.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2019/12/27 16:14
 */
@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/update")
    public String updateAdmin(Admin admin) {

        try {
            adminService.updateAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof DuplicateKeyException) {
                throw new RuntimeException(CrowdFundingConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
        return "redirect:/admin/query/for/search.html";
    }


    @RequestMapping("/admin/to/edit/page")
    public String toEditPage(@RequestParam("adminId") Integer adminId, Model model) {

        Admin admin = adminService.getAdminById(adminId);

        model.addAttribute("admin", admin);

        return "admin-edit";
    }



    @RequestMapping("/admin/save")
    public String saveAdmin(Admin admin) {
        try {
            adminService.saveAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof DuplicateKeyException) {
                throw new RuntimeException(CrowdFundingConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
        //return "redirect:/admin/query/for/search.html";
        // 操作完成后立即看到新增的记录,跳转到分页页面时前往最后一页
        return "redirect:/admin/query/for/search.html?pageNum="+Integer.MAX_VALUE;
    }



    // handler方法
// 将当前handler方法的返回值作为响应体返回，不经过视图解析器
    @ResponseBody
    @RequestMapping("/admin/batch/remove")
    public ResultEntity<String> batchRemove(@RequestBody List<Integer> adminIdList) {
        try {
            adminService.batchRemove(adminIdList);
            return ResultEntity.successWithoutData();
        }catch(Exception e) {
            return ResultEntity.failed(null, e.getMessage());
        }
    }




    @RequestMapping("/admin/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/index.html";
    }

    @RequestMapping("/admin/query/for/search")
    public String queryForSearch(
            // 如果页面上没有提供对应的请求参数，那么可以使用defaultValue指定默认值
            @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
            @RequestParam(value="pageSize", defaultValue="10") Integer pageSize,
            @RequestParam(value="keyword", defaultValue="") String keyword,
            Model model) {

        PageInfo<Admin> pageInfo = adminService.queryForKeywordSearch(pageNum, pageSize, keyword);

        model.addAttribute(CrowdFundingConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "admin-page";
    }


    @RequestMapping("admin/do/login")
    public  String doLogin(@RequestParam("loginAcct") String loginAcct,
                           @RequestParam("userPswd") String userPswd,
                                       Model model,
                           HttpSession session){
        // 调用adminService的login方法执行登录业务逻辑，返回查询到的Admin对象
        Admin admin = adminService.login(loginAcct, userPswd);

        //判断admin是否为null
        if(admin == null) {

            model.addAttribute(CrowdFundingConstant.ATTR_NAME_MESSAGE, CrowdFundingConstant.MESSAGE_LOGIN_FAILED);

            return "admin-login";
        }
        session.setAttribute(CrowdFundingConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";
    }




/*    @RequestMapping("/admin/to/login/page")
    public String toLoginPage(){
        return "admin-login";
    }*/

    @RequestMapping("/admin/get/all")
    public String getAll(Model model) {

        List<Admin> list = adminService.getAll();

        model.addAttribute("list", list);

        return "admin-target";
    }
}
