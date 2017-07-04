package com.kazoku.httpmodel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.kazoku.exceptions.MissingIdentityException;
import com.kazoku.httpmodel.filters.HeaderInitFilter;
import com.kazoku.models.RequestIdentity;

public class RequestIdentityManager {

	private static Logger log = Logger.getLogger(RequestIdentityManager.class.getName());
	
	private String requestIdentityHeader;
	
	private static RequestIdentityManager rim = null;
	
	
	public RequestIdentityManager()
	{
		super();
	}
	
	public RequestIdentityManager(String requestHeader){
		this.requestIdentityHeader = requestHeader;
	}

	public RequestIdentity createRequestIdentity() {
		log.debug("Processing caller's identity in NTLMIdentityCreator");

		ServletExternalContext context = HeaderInitFilter.getServletExternalContext();
		if (context == null) {
			log.info(
					"No 'ServletExternalContext' found in the 'HeaderInitFilter'. The application may not be configured with the filter");
			return null;
		}

		HttpServletRequest request = context.getHttpServletRequest();

		Cookie[] cookies = request.getCookies();

		String principal = null;
		String roles = null;

		if (cookies != null) {
			for (Cookie co : cookies) {
				if (co.getName().equals(requestIdentityHeader)) {
					principal = co.getValue().toUpperCase();
				}
			}
		}

		log.debug("Principal read from authentication request header is '" + principal + "'");

		RequestIdentity info = null;

		if (principal == null) {
			throw new MissingIdentityException();
		} else {
			info = new RequestIdentity();
			info.setUser(principal);
			info.setHost(request.getRemoteHost());
			info.setIpAddress(request.getRemoteAddr());

			log.debug("IdentityInfo is '" + info + "'");
		}

		return info;
	}
	
	public static void init(String requestIdentityHeader)
	{
		rim = new RequestIdentityManager(requestIdentityHeader);
	}
	
	
	public static synchronized RequestIdentityManager getManager(){
		
		if(rim == null)
			rim = new RequestIdentityManager();
		
		return rim;
	}

}
