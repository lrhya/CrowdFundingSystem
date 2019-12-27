package com.lrh.crowd.funding.test;


import com.lrh.crowd.funding.entity.Admin;
import com.lrh.crowd.funding.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2019/12/27 14:23
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})

public class CrowdFundingTest {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminService adminService;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void  testMybatis(){
        List<Admin> adminList =adminService.getAll();
        for (Admin admin: adminList) {
            System.out.println(admin);
        }
    }

}
