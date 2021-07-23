package com.awei.controller;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class MyErrorController implements ErrorController {
    /*出异常后进入该方法，交由下面的方法处理*/
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public String error(){
        ZuulException exception = (ZuulException) RequestContext.getCurrentContext().getThrowable();
        /**
         * 使用全局异常页面，这里可以直接转向到一个视图页面显示错误信息，也可以直接使用Json来显示错误信息
         *  但是使用全局异常错误页面必须要启动默认的异常过滤器，因此全局异常页面和自定义异常过滤器有冲突，二选一即可
         *
         **/
        return "全局错误页面Code :"+exception.nStatusCode+"===message==="+exception.getMessage();
    }
}
