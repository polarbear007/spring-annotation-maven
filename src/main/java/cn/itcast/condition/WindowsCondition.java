package cn.itcast.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition{
	
	// 这个 matches 方法返回的结果决定着是否加载这个方法或者这个配置类
	// ConditionContext 参数可以获取上下文信息，比如BeanFactory 、 Environment、 ResourceLoader、 ClassLoader、 BeanDefinitionRegistry等
	// AnnotatedTypeMetadata 参数可以获取 类上面或者方法上面的注解信息
	// 我们可以根据这两个参数所获取的信息，来决定是否加载这个方法或者加载这个配置类上面的配置信息
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 这里我们只演示一下，如果运行的系统是 windows 的话，那么我们就往容器里面添加一个名为 Bill Gates 的student对象
		String os = context.getEnvironment().getProperty("os.name");
		if(os != null && os.toLowerCase().contains("windows")) {
			return true;
		}
		return false;
	}

}
