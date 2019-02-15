package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.itcast.entity.StudentFactoryBean;

@Configuration
public class FactoryBeanConfig {
	// 【注意】 虽然我们这里返回值是 StudentFactoryBean ，但是因为这个返回值实现了 FactoryBean 接口
	//        所以 spring 在注册组件的时候，并不会直接把 StudentFactoryBean 对象放进容器中，而是再去调用
	//        getObject() 方法，再把这个方法的返回值放进容器中
	
	//        然后我们在测试类中获取对象的时候，通过  “student” id 获取到的其实是 Student 对象，而不是
	//        StudentFactoryBean 对象， 如果我们想要获取 StudentFactoryBean 对象本身，那么就使用  “&student”
	//        这个 id 去获取。
	@Bean("student")
	public StudentFactoryBean getStudent() {
		return new StudentFactoryBean();
	}
}
