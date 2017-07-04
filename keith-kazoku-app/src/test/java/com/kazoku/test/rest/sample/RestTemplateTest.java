package com.kazoku.test.rest.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kazoku.httpmodel.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/kazoku.xml")
@WebAppConfiguration
public class RestTemplateTest {
	
	
	@Autowired
	RestTemplate template;
	
	
	@Test
	public void restTemplateTest(){
		
		HttpEntity<String> request = new HttpEntity<String>(new HttpHeaders());
		
		Object response = template.template().exchange("https://httpbin.org/get", HttpMethod.GET, request, Object.class);
		
	}
	
	

}
