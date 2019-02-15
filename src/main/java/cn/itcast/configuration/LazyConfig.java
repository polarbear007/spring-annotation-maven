package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import cn.itcast.entity.Teacher;

@Configuration
public class LazyConfig {
	@Bean("teacher")
	@Lazy(true)
	public Teacher teacher() {
		System.out.println("创建 teacher 对象....");
		return new Teacher("李老师", 20);
	}
}
