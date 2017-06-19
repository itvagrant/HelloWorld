package com.srpingdemo.day1;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基准类
 * @author bwfadmin
 */
/*
 * @RunWith指定是junit4单元测试框架
 * @ContextConfiguration用来加载相关配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath*:/spring/spring-dao.xml",
	"classpath*:/spring/spring-service.xml"
})
public abstract class BaseTest {
	
}
