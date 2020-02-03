package com.lrh.crowd.funding.config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/3 14:01
 */
public class BCryptPasswordEncoderTest {

    /*
    一个明文对应多个不相等的密文
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        CharSequence rawPassword = "123123";
        for(int i = 0; i < 10; i++) {

            String encodedPassword = encoder.encode(rawPassword);
            System.out.println(encodedPassword);
        }

        boolean matches = encoder.matches(rawPassword, "$2a$10$Y2Cq8ilT21ME.lvu6bwcPO/RMkU7ucAZpmFzx7GDTXK9KNxHyEM1e");
        System.out.println(matches);

    }
}
