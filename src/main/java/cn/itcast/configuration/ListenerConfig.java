package cn.itcast.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 使用包扫描一下监听器所有包下的 组件注解 
@ComponentScan("cn.itcast.listener")
@Configuration
public class ListenerConfig {
	// 只是为了演示一下监听器，直接不配置其他的 bean
}
