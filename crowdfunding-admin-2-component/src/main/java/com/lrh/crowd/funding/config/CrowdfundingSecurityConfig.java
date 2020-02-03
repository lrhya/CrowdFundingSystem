package com.lrh.crowd.funding.config;

import com.lrh.crowd.funding.exception.CrowdFundingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/2 15:03
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class CrowdfundingSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    // Spring在真正调用这个方法前会检查，IOC容器中是否已经有了对应的bean，
    // 如果有，则不会真正调用这个方法。而是直接把IOC容器中的bean返回。
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

          //	测试使用
/*		builder
			.inMemoryAuthentication()
			.withUser("xixi")
			.password("12323")
			.roles("king");*/
      /*  builder.userDetailsService(userDetailsService);*/
        builder.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.authorizeRequests()
                .antMatchers("/index.html","/bootstrap/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**")
                .permitAll()
                .antMatchers("/admin/query/for/search.html")
                .hasRole("PM_项目经理")
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CrowdFundingAccessDeniedHandler())
                .and()
                .formLogin()
                .loginPage("/admin/to/login/page.html")
                .permitAll()
                .loginProcessingUrl("/admin/security/do/login.html")
                .permitAll()
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                .defaultSuccessUrl("/admin/to/main/page.html")
                .and()
                .logout()
                .logoutUrl("/admin/security/do/logout.html")
                .logoutSuccessUrl("/index.html")
                .and()
                .csrf()
                .disable();


    }
}
