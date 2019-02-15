package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cn.itcast.entity.BeanLifeCycle;
import cn.itcast.entity.MyBeanPostProcessor;

@Configuration
@Import({MyBeanPostProcessor.class})
public class BeanLifeCycleConfig {
	@Bean(initMethod="myInit", destroyMethod="myDestroy")
	public BeanLifeCycle beanLifeCycle() {
		BeanLifeCycle beanLifeCycle = new BeanLifeCycle();
		beanLifeCycle.setParam("hello world");
		return beanLifeCycle;
	}
}
