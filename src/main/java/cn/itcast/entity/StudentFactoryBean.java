package cn.itcast.entity;

import org.springframework.beans.factory.FactoryBean;

// 接口的泛型就是我们要创建的那个类型 
public class StudentFactoryBean implements FactoryBean<Student> {
	@Override
	public Student getObject() throws Exception {
		return new Student("张三", 12);
	}
	// 设置返回值的类型
	@Override
	public Class<?> getObjectType() {
		return Student.class;
	}
	// 设置是否为单实例
	@Override
	public boolean isSingleton() {
		return true;
	}
}
