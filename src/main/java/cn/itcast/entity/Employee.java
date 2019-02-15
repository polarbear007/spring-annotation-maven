package cn.itcast.entity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public class Employee {
	// 注入一个普通的值
	@Value("王小明")
	private String name;
	// 使用 el 表达式返回一个 date 对象
	@Value("#{new java.text.SimpleDateFormat('yyyy-MM-dd').parse('2000-1-1') }")
	private Date birthday;
	// 引用 .properties 文件里面的值
	@Value("${shanghai.position.it}")
	private String position;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", birthday=" + birthday + ", position=" + position + "]";
	}
	
	
}
