package cn.itcast.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanLifeCycle implements  	BeanNameAware, 
										BeanFactoryAware, 
										ApplicationContextAware, 
										InitializingBean, 
										DisposableBean {
	// 提供一个成员变量，演示 setter 注入
	private String param;
	
	// 无参构造方法
	public BeanLifeCycle() {
		super();
		System.out.println(this.getClass().getSimpleName() + "的构造方法执行了！");
	}
	
	// 为成员变量 param 注入初始化值
	public void setParam(String param) {
		this.param = param;
		System.out.println(this.getClass().getSimpleName() + "的 setter 方法执行了，为本对象进行依赖注入");
	}
	
	public String getParam() {
		return this.param;
	}

	// 实现BeanFactoryAware 接口，需要重写此方法
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryAware: 选择了" + beanFactory + "来创建对象！");
	}
	
	// 实现BeanNameAware 接口，需要重写此方法
	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware: 根据核心配置文件（id或者name），本对象的名称确定为：" + name );
	}
	
	// 实现 ApplicationContextAware 接口，需要重写此方法
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationContextAware: 已经把创建好的对象以id为键放进了spring容器中了，你可以通过容器获取已经创建好的对象");
	}

	// 实现 DisposableBean 接口，需要重写此方法
	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean : 调用destroy() 方法");
	}

	// 实现 InitializingBean 接口，需要重写此方法
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean : 调用了afterPropertiesSet() 方法");
	}

	// 自定义的 init() 方法，可以在 <bean> 标签中指定 
	public void myInit() {
		System.out.println("自定义的init() 方法执行了！");
	}

	// 自定义的 destroy() 方法，可以在 <bean> 标签中指定 
	public void myDestroy() {
		System.out.println("自定义的 destroy() 方法执行了！");
	}
	
	// 其他的方法
	public void test() {
		System.out.println("执行test 方法，说明可以从spring容器获取对象，并执行方法了");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("@PostConstruct 标记的方法执行了");
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("@PreDestroy 标记的方法执行了");
	}
	
}
