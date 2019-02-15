package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import cn.itcast.entity.Student;
import cn.itcast.entity.Teacher;


@ComponentScan(excludeFilters= {
								 @Filter(type=FilterType.ANNOTATION, classes= {Controller.class})
								},
			   basePackages="cn.itcast"
              )
@Configuration
public class MainConfig {
	// 如果我们@Bean 没有使用任何参数的话，那么（class）类型就是方法的返回值类型
	// id 或者说别名就是指 方法名
	@Bean
	public Student student1() {
		return new Student("小明", 12);
	}
	
	@Bean
	public Student student2() {
		return new Student("小花", 12);
	}
	
	// 如果我们在 @Bean 注解里面使用了参数的话，那么id 就是使用参数指定的名字
	@Bean("monitor")
	public Student  student() {
		return new Student("二狗", 12);
	}
	
	@Scope(scopeName="prototype")
	@Bean("teacher")
	public Teacher teacher() {
		return new Teacher("王老师", 30);
	}
	
	@Lazy(true)
	@Scope(scopeName="singleton")
	@Bean("teacher2")
	public Teacher teacher2() {
		return new Teacher("王老师2", 40);
	}
}
