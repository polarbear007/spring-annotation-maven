package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import cn.itcast.condition.LinuxCondition;
import cn.itcast.condition.WindowsCondition;
import cn.itcast.entity.Student;

@Configuration
public class ConditionalConfig {
	@Bean("Bill Gates")
	@Conditional({WindowsCondition.class})
	public Student student1() {
		return new Student("Bill Gates", 20);
	}
	
	@Bean("Linus")
	@Conditional({LinuxCondition.class})
	public Student student2() {
		return new Student("Linux", 20);
	}
}
