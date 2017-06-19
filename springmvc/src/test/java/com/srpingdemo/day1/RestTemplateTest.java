package com.srpingdemo.day1;

import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.srpingdemo.day1.entity.User;

public class RestTemplateTest {
	private RestTemplate restTemp = new RestTemplate();
	
	@Test
	public void findText(){
		String result = restTemp.getForObject("http://192.168.40.153:8080/springmvcdemo01/otherrest/text",String.class);
		System.out.println(result);
	}
	
	@Test
	public void findUsers(){
		List<User> result = restTemp.getForObject("http://192.168.40.153:8080/springmvcdemo01/otherrest/users",List.class);
		System.out.println(result);
	}
	
}
