package com.lrh.crowd.funding.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lrhya
 * @version 1.0
 * @date 2019/12/30 15:26
 */
@Controller
public class PortalHandler {

    @RequestMapping("/index")
    public String showIndex(){
        return "admin-login";
    }

}
