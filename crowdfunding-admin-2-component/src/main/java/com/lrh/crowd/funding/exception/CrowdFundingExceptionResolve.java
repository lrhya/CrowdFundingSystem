package com.lrh.crowd.funding.exception;

import com.lrh.crowd.funding.CrowdFundingConstant;
import com.lrh.crowd.funding.CrowdFundingUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/2 11:18
 */
@ControllerAdvice
public class CrowdFundingExceptionResolve {

    @ExceptionHandler(value=Exception.class)
    public ModelAndView catchException(Exception exception)  {

        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", exception);

        mav.setViewName("system-error");

        return mav;
    }


}
