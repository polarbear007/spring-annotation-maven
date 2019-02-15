package cn.itcast.configuration;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import cn.itcast.service.StudentService;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	// AnnotationMetadata 可以获取目标类的各种信息，我们在 ImportSelector 接口的方法里面已经演示过了
	
	// BeanDefinitionRegistry : 我们可以直接使用这个参数往 IOC容器中注册组件
	//                          也可以使用这个参数删除 IOC容器中的组件
	//                           也可以查看IOC容器是否包含某个组件 
	//                           也可以查看IOC容器中所有的组件
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("这里就不再演示 AnnotationMetadata 可以获取哪些信息了");
		System.out.println("===========================");
		
		System.out.println("现在容器中有多少个bean 对象：" + registry.getBeanDefinitionCount());
		
		// 查看所有的组件名 
		String[] names = registry.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		
		// 如果容器中包含 student 的对象，我们就把这个对象删除 
		if(registry.containsBeanDefinition("cn.itcast.entity.Student")) {
			registry.removeBeanDefinition("cn.itcast.entity.Student");
		}
		
		// 如果容器中不包含  StudentService 对象，那么就手动添加一个 StudentService 对象到容器中
		if(!registry.containsBeanDefinition("studentService") && !registry.containsBeanDefinition("cn.itcast.service.studentService")) {
			registry.registerBeanDefinition("studentService", new RootBeanDefinition(StudentService.class));
		}
	}

}
