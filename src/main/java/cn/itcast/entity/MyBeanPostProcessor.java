package cn.itcast.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessor postProcessBeforeInitialization(): 使用了勾子程序对" + beanName + 
							"对象进行拦截：可以在这个对象初始化前执行某些操作， 也可以生成代理类，对这个对象的某些方法进行加强");
		
		// 【注意】： 如果我们返回的是 bean 对象本身，那么说明我们并没有修改 bean 对象
		//         如果我们返回的是 bean 对象的代理对象，那么容器里面的 bean 对象就会被替换成代理对象了
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessor postProcessAfterInitialization(): 使用了勾子程序对" + beanName + 
				"对象进行拦截：可以在这个对象初始化后执行某些操作， 也可以生成代理类，对这个对象的某些方法进行加强");
		return bean;
	}

}
