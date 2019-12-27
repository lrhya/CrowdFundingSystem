package com.lrh.crowd.funding.handler;

import com.lrh.crowd.funding.entity.Admin;
import com.lrh.crowd.funding.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2019/12/27 16:14
 */
@Controller
public class AdminHandle {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/get/all")
    public String getAll(Model model) {

        List<Admin> list = adminService.getAll();

        model.addAttribute("list", list);

        return "admin-target";
    }
}
