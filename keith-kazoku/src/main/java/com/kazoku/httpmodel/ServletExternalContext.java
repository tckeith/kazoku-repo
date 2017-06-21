package com.kazoku.httpmodel;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

 public class ServletExternalContext
 {
    private final ServletContext context;    
    private final HttpServletRequest request;

    public ServletExternalContext( ServletContext context, HttpServletRequest request )
    {
          this.context = context; 
          this.request = request;
    }  

    public ServletContext getServletContext()
    {
           return context;
    }

    public HttpServletRequest getHttpServletRequest()
    {
           return request;
    }
 }
