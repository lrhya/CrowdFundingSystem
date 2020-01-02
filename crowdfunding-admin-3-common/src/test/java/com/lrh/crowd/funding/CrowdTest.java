package com.lrh.crowd.funding;

import org.junit.Test;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/2 10:29
 */
public class CrowdTest {


    @Test
    public  void  testMD5(){

        String source ="123123";
        String target = CrowdFundingUtils.md5(source);

        System.out.println(target);

    }
}
