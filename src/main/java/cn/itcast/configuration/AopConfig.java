package cn.itcast.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import cn.itcast.entity.MyAspect;
import cn.itcast.entity.MyMath;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)  // 一定要启用这个
public class AopConfig {
	@Bean
	public MyMath myMath() {
		return new MyMath();
	}
	
	// 因为我们没有配置包扫描，所以这里还是得手动添加一个 切面类对象
	// 如果我们配置了包扫描的话，那么就不需要再特意去注册切面类了
	@Bean
	public MyAspect myAspect() {
		return new MyAspect();
	}
}
