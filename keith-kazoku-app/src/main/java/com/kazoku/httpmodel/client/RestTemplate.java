package com.kazoku.httpmodel.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class RestTemplate {
	
	public org.springframework.web.client.RestTemplate template(){
		
		org.springframework.web.client.RestTemplate template = new org.springframework.web.client.RestTemplate((ClientHttpRequestFactory) new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new RequestInterceptor());
		template.setInterceptors(interceptors);
		
		return template;
	}

}
