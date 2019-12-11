package com.cai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class WordsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request;
        HttpServletResponse response;
        try{
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)resp;
        }catch(Exception e){
            throw new RuntimeException("non-http request");
        }

        DirtyWordHttpServletRequest dwrequest = new DirtyWordHttpServletRequest(request);
        chain.doFilter(dwrequest, response);

    }


    @Override
    public void destroy() {

    }

}

class DirtyWordHttpServletRequest extends HttpServletRequestWrapper{
    private static List<String> list = new ArrayList<>();
    static {
        list.add("sb");
        list.add("傻子");
        list.add("秃了");
        list.add("暴力");
        list.add("血腥");
    }

    private HttpServletRequest request;

    public DirtyWordHttpServletRequest(HttpServletRequest request){

        super(request);
        this.request = request;

    }

    @Override
    public String getParameter(String name) {

        String value = request.getParameter(name);
        if(value==null) {
            return null;
        }
        for(String word : list){
            value = value.replaceAll(word, "**");
        }
        return value;
    }

}
