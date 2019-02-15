package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.itcast.entity.TestBean;

@Configuration
public class TestConfig {
	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
}
