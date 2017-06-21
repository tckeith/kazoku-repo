package com.kazoku.httpmodel.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.kazoku.httpmodel.ServletExternalContext;

public class HeaderInitFilter implements Filter
{
	private final static ThreadLocal externalContextTL = new ThreadLocal();

	private ServletContext servletContext;

	public void init( FilterConfig fc ) throws ServletException
	{
		servletContext = fc.getServletContext();
	}

	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		if (request instanceof HttpServletRequest)
		{
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			externalContextTL.set( new ServletExternalContext( servletContext, httpRequest ) );
		}
		try
		{
			chain.doFilter(request, response);
		}
		finally
		{
		        externalContextTL.set(null);
		}
	}

	public void destroy()
	{
	}

	public static ServletExternalContext getServletExternalContext()
	{
		return (ServletExternalContext) externalContextTL.get();
	}
}

