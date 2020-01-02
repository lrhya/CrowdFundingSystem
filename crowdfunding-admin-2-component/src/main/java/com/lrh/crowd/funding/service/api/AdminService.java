package com.lrh.crowd.funding.service.api;

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

    Admin login(String loginAcct, String userPswd);
}
