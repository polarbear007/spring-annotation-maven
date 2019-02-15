package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import cn.itcast.entity.Employee;

@Configuration
@PropertySource("classpath:position.properties") // 引入一个 .properties 文件
public class DIConfig {
	@Bean
	public Employee employee() {
		return new Employee();
	}
}
