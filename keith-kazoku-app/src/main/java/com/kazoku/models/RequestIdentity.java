package com.kazoku.models;

import java.security.Principal;

public class RequestIdentity {

	private String user;
	private String host;
	private String domain;
	private String ipAddress;
	private Principal principal;
	
	public RequestIdentity() {
		super();
	}

	public RequestIdentity(String user) {
		super();
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Principal getPrincipal() {
		return principal;
	}
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	
	
	

}
