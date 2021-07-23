package com.awei.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        ZuulException exception = (ZuulException)context.getThrowable();
        HttpServletResponse response = context.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(exception.nStatusCode);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("code: "+exception.nStatusCode+", message :\""+exception.getMessage()+"\"");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                writer.close();
            }
        }

        return null;
    }
}
