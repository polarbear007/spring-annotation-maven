package cn.itcast.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
	// 注意， 这个方法就是返回一个空的字符串数组，也不能返回一个 null 
	// 如果返回 null ，后面会直接报空指针异常
	
	// AnnotationMetadata  可以获取目标配置类的各种信息
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		String className = importingClassMetadata.getClassName();
		System.out.println("目标类的名字：" + className);
		String enclosingClassName = importingClassMetadata.getEnclosingClassName();
		System.out.println("目标类的包装类（或者说外部类，说明本类是一个内部类）信息：" + enclosingClassName);
		String superClassName = importingClassMetadata.getSuperClassName();
		System.out.println("目标类的父类信息：" + superClassName);
		String[] interfaceNames = importingClassMetadata.getInterfaceNames();
		System.out.println("目标类实现的接口信息：" + interfaceNames);
		String[] memberClassNames = importingClassMetadata.getMemberClassNames();
		System.out.println("目标类的成员内部类信息：" + memberClassNames);
		System.out.println("其实还可以获取一些注解的配置信息，但是这里就不再演示了");
		System.out.println("我们可以根据这些条件，动态地选择要添加什么组件到IOC容器中");
		System.out.println("============================================");
		String[] selects = {"cn.itcast.controller.StudentController", "cn.itcast.mapper.StudentMapper"};
		return selects;
	}

}
