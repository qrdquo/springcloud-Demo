package com.awei.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Component
public class AuthFilter extends ZuulFilter {
    //当前方法的返回值用于决定当前过滤器的类型(执行时机)
    @Override
    public String filterType() {
        //返回pre表示当前过滤器为前置过滤器，需要在执行转发(访问服务提供者)前执行，通常用于做身份认证
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤器的序号，若果有多个同类型的过滤那么根据这个返回值大小决定执行的先后顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //当前过滤器是否启动，true默认启动
        return true;
    }

    private  String getBase64Credentials(String username, String password) {
        String plainCreds = username +  ":"  + password;
        byte [] plainCredsBytes = plainCreds.getBytes();
        byte [] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        return  new  String(base64CredsBytes);
    }
    @Override
    //过滤器的具体执行方法
    public Object run() throws ZuulException {
        //int a = 10/0;
        //获取当前请求上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();

        currentContext.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("liyawei", "123456"));
        //获取用户请求对象
        HttpServletRequest request = currentContext.getRequest();
        //获取请求中的请求参数token(身份令牌用于验证请求身份是否合法)
        String token = request.getParameter("token");
        //验证令牌有效性，进入if表示q请求没有身份令牌或令牌错误
        //实际工作中123是从数据库中查询
        if(token==null){
            //false表示请求不继续执行(不转发给服务提供者)
            currentContext.setSendZuulResponse(false);
            //设置响应编码为401表示权限不足也可以设置500或其它码
            currentContext.setResponseStatusCode(401);
            //设置响应类型及其编码格式
            currentContext.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            //设置响应内容
            currentContext.setResponseBody("非法请求");
        }
        return null;
    }
}
