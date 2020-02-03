package com.lrh.crowd.funding.config;

import com.lrh.crowd.funding.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/3 12:31
 */
public class SecurityAdmin extends User {

    private static final long serialVersionUID = 1L;

    private Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin, Collection<GrantedAuthority> authorities) {

        // 调用父类的构造器
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);

        this.originalAdmin = originalAdmin;

    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
