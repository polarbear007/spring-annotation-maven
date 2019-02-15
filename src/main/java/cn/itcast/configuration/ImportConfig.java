package cn.itcast.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cn.itcast.entity.Student;
import cn.itcast.entity.Teacher;
import cn.itcast.selector.MyImportSelector;

@Configuration
@Import({Student.class, Teacher.class, MyImportSelector.class, cn.itcast.configuration.MyImportBeanDefinitionRegistrar.class})
public class ImportConfig {
	
}
