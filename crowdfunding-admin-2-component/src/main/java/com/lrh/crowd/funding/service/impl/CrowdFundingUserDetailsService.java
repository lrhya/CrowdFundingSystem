package com.lrh.crowd.funding.service.impl;

import com.lrh.crowd.funding.CrowdFundingUtils;
import com.lrh.crowd.funding.config.SecurityAdmin;
import com.lrh.crowd.funding.entity.Admin;
import com.lrh.crowd.funding.entity.AdminExample;
import com.lrh.crowd.funding.entity.Auth;
import com.lrh.crowd.funding.entity.Role;
import com.lrh.crowd.funding.mapper.AdminMapper;
import com.lrh.crowd.funding.mapper.AuthMapper;
import com.lrh.crowd.funding.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/3 12:25
 */
@Service
public class CrowdFundingUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.创建Example对象
        AdminExample adminExample = new AdminExample();

        // 2.封装查询条件
        adminExample.createCriteria().andLoginAcctEqualTo(username);

        // 3.执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);

        // 4.检查list是否有效
        if(!CrowdFundingUtils.collectionEffective(list)) {
            return null;
        }

        // 5.从list中取出Admin对象
        Admin admin = list.get(0);

        // 6.获取密码
        String userPswd = admin.getUserPswd();

        // 7.封装角色、权限信息
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_学生","ROLE_班长","扫地");


/*        // [1]封装角色信息
        // ①创建存储角色、权限信息的集合
        List<GrantedAuthority> authorities = new ArrayList();

        // ②获取adminId
        Integer adminId = admin.getId();

        // ③查询分配给当前Admin的角色
        List<Role> roleList = roleMapper.selectAssignedRoleList(adminId);

        // ④遍历角色集合
        for (Role role : roleList) {

            // ⑤不要忘记加前缀！！！
            String roleName = "ROLE_" + role.gettName();

            // ⑥创建SimpleGrantedAuthority对象存入集合
            authorities.add(new SimpleGrantedAuthority(roleName));

        }

        // [2]封装权限信息
        // ①查询当前Admin对应的权限
        List<Auth> authList = authMapper.selectAuthListByAdminId(adminId);

        // ②遍历
        for (Auth auth : authList) {

            // ③这里不加前缀！！！
            String authName = auth.getName();

            // ※特殊处理：authName如果不是有效字符串，则抛弃
            if(!CrowdFundingUtils.stringEffective(authName)) {
                continue ;
            }

            // ④创建SimpleGrantedAuthority对象存入集合
            authorities.add(new SimpleGrantedAuthority(authName));

        }*/

        // 8.返回User对象
        return new User(username,userPswd, authorities);
    }
}
