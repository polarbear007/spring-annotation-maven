package cn.itcast.listener;

import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener{
	// 我们可以在任意实体类上添加 @EventListener 注解
	// 这个被标记的方法就相当于我们实现了 ApplicationListener 接口，重写的 onApplicationEvent() 方法
	// 我们可以在@EventListener 注解里面添加要监听哪些事件，直接把那些事件的Class 对象添加到数组中即可
	// 方法中，也可以添加一个事件对象，我们建议写 ApplicationEvent 
	@EventListener({ContextRefreshedEvent.class})
	public void listener1(ApplicationEvent event) {
		System.out.println("监听器1："+ new Date(event.getTimestamp()) +" :容器刷新了...");
	}
	
	@EventListener({ContextClosedEvent.class})
	public void listener2(ApplicationEvent event) {
		System.out.println("监听器2："+ new Date(event.getTimestamp()) +" :容器关闭了...");
	}
}
