package cn.itcast.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringValueResolver;

import cn.itcast.configuration.AopConfig;
import cn.itcast.configuration.AwareConfig;
import cn.itcast.configuration.BeanLifeCycleConfig;
import cn.itcast.configuration.ConditionalConfig;
import cn.itcast.configuration.ControllerConfig;
import cn.itcast.configuration.DIConfig;
import cn.itcast.configuration.FactoryBeanConfig;
import cn.itcast.configuration.ImportConfig;
import cn.itcast.configuration.LazyConfig;
import cn.itcast.configuration.ListenerConfig;
import cn.itcast.configuration.MainConfig;
import cn.itcast.configuration.ProfileConfig;
import cn.itcast.configuration.TestConfig;
import cn.itcast.configuration.TxConfig;
import cn.itcast.entity.BeanLifeCycle;
import cn.itcast.entity.Employee;
import cn.itcast.entity.MyMath;
import cn.itcast.entity.Person;
import cn.itcast.entity.Student;
import cn.itcast.entity.Teacher;
import cn.itcast.entity.TestBean;
import cn.itcast.service.StudentService;

public class IocTest {
	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student student1 = context.getBean("student1", Student.class);
		System.out.println(student1);
		
		Student student2 = context.getBean("student2", Student.class);
		System.out.println(student2);
		
		context.close();
	}
	
	@Test
	public void test2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		Student student1 = context.getBean("student1", Student.class);
		System.out.println(student1);
		
		Student student2 = context.getBean("student2", Student.class);
		System.out.println(student2);
		
		Student monitor = context.getBean("monitor", Student.class);
		System.out.println(monitor);
		
		context.close();
	}
	 
	// 我们来演示一下如何作用注解来扫描指定包下的 组件注解的
	// 主要的配置还是在 MainConfig 上面，我们这里只是打印一下容器中有哪些 bean 对象
	// 使用excludeFilter 参数
	@Test
	public void test3() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		
		// 获取容器中 bean 对象的数量
		System.out.println("容器中 bean 对象的数量：" + context.getBeanDefinitionCount());
		
		// 遍历容器中所有定义的 bean 的名字（或者说 id）
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		
		context.close();
	}
	
	// 演示注解扫描的另一个配置，只扫描 controller 相关的注解
	// 使用 includeFilter 参数
	@Test
	public void test4() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class);
		
		// 获取容器中 bean 对象的数量
		System.out.println("容器中 bean 对象的数量：" + context.getBeanDefinitionCount());
		
		// 遍历容器中所有定义的 bean 的名字（或者说 id）
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		context.close();
	}
	
	@Test
	public void test5() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		
		Teacher t1 = context.getBean("teacher", Teacher.class);
		Teacher t2 = context.getBean("teacher", Teacher.class);
		
		// 如果我们没有使用 @Scope(scopeName="prototype") 注解的话，那么这个默认是返回 true
		// 如果我们使用了  @Scope(scopeName="prototype")， 那么每次 getBean() 都会创建一个新的对象，则返回 false
		System.out.println(t1 == t2);
		context.close();
	}
	
	@Test
	public void test6() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyConfig.class);
		// 我们在创建完 context 对象以后，打印一下下面的语句
		// 如果配置了懒加载的话，那么在下面的语句会先打印，然后获取对象的时候，再打印创建对象的语句
		// 如果我们没有配置懒加载的话，那么在初始化容器对象的时候，创建对象的语句就已经打印了
		System.out.println("ioc容器初始化完成......");
		
		Teacher teacher = context.getBean("teacher", Teacher.class);
		System.out.println(teacher);
		context.close();
	}
	
	@Test
	public void test7() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionalConfig.class);
		// 获取容器中 bean 对象的数量
		System.out.println("容器中 bean 对象的数量：" + context.getBeanDefinitionCount());
		
		// 遍历容器中所有定义的 bean 的名字（或者说 id）
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		context.close();
	}
	
	@Test
	public void test8() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
		// 获取容器中 bean 对象的数量
		System.out.println("容器中 bean 对象的数量：" + context.getBeanDefinitionCount());
		
		// 遍历容器中所有定义的 bean 的名字（或者说 id）
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		context.close();
	}
	
	@Test
	public void test9() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
		Student student1 = context.getBean("student", Student.class);
		System.out.println(student1);
		Student student2 = context.getBean("student", Student.class);
		System.out.println(student2);
		// 如果我们的 isSingleton() 方法返回的是 true ，那么这里就会返回 true
		System.out.println(student1 == student2);
		
		// 我们看一下容器到底有没有哪些对象
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		
		// 我们尝试获取  StudentFactoryBean 对象本身 
		Object bean = context.getBean("&student");
		System.out.println(bean.getClass().getName());
		context.close();
	}
	
	@Test
	public void test10() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
		BeanLifeCycle beanLifeCyCle = context.getBean("beanLifeCycle", BeanLifeCycle.class);
		beanLifeCyCle.test();
		
		context.close();
	}
	
	@Test
	public void test11() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);
		BeanLifeCycle beanLifeCycle = context.getBean("beanLifeCycle", BeanLifeCycle.class);
		beanLifeCycle.test();
		context.close();
	}
	
	@Test
	public void test12() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfig.class);
		Employee emp = context.getBean("employee", Employee.class);
		System.out.println(emp);
		context.close();
	}
	
	@Test
	public void test13() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		Person person = context.getBean("person", Person.class);
		// 从person 对象里面获取容器对象
		ApplicationContext context2 = person.getApplicationContext();
		
		// 比较一下 person 里面获取的容器对象跟这里的容器对象是否是同一个对象
		System.out.println(context ==  context2);
		context.close();
	}
	
	@Test
	public void test14() throws SQLException {
		// 如果我们想要激活某种   profile 的配置，那么就不能使用带参的构造，只能使用无参构造
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		// 在容器刷新之前，先设置要激活哪个 profile 配置
		// 我们可以配置  "dev" / "test" / "produce"
		//  其实不配置的话，默认是 "default"
		context.getEnvironment().setActiveProfiles("test");
		// 然后我们加载配置类
		context.register(ProfileConfig.class);
		// 然后再手动刷新容器---> 其实就是加载配置类，然后根据配置类信息去初始化容器
		context.refresh();
		
		// 然后我们可以看一下现在容器中有哪些对象
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
		
		// 试着看能不能获取对应的 connection 对象
		// 我们再从 connection 对象去获取对应的 catalog ，也就是对应的数据库名，看能不能正常切换
		DataSource dataSource = context.getBean(DataSource.class);
		System.out.println(dataSource.getConnection().getCatalog());
		
		context.close();
	}
	
	@Test
	public void test15() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		MyMath math = context.getBean("myMath", MyMath.class);
		// 我们可以故意让除数为 0， 演示一下发生异常的情况
		//math.divide(10, 0);
		math.divide(10, 2);
		System.out.println("====================");
		math.hello();
		context.close();
	}
	
	@Test
	public void test16() throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
		StudentService service = context.getBean(StudentService.class);
		
		Student stu = new Student();
		stu.setName("小明");
		stu.setAge(12);
		
		service.insertStudent(stu);	
		
		context.close();
	}
	
	@Test
	public void test17() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		Person person = context.getBean("person", Person.class);
		
		// 获取这个 Resolver 对象
		StringValueResolver resolver = person.getResolver();
		// 使用这个 对象，我们可以获取加载到容器中的  .properties 文件里面的内容 
		String string = resolver.resolveStringValue("${shanghai.position.it}");
		System.out.println(string);
		context.close();
		
	}
	
	@Test
	public void test18() throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
		DataSource testDataSource = context.getBean("testDataSource", DataSource.class);
		System.out.println(testDataSource.getConnection().getCatalog());
		
		DataSource devDataSource = context.getBean("devDataSource", DataSource.class);
		System.out.println(devDataSource.getConnection().getCatalog());
		
		DataSource produceDataSource = context.getBean("produceDataSource", DataSource.class);
		System.out.println(produceDataSource.getConnection().getCatalog());
		
		context.close();
	}
	
	@Test
	public void test19() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		TestBean bean = context.getBean(TestBean.class);
		EnvironmentAware bean2 = context.getBean(EnvironmentAware.class);
		
		System.out.println(bean2.getClass().getName());
		System.out.println(bean.getApplicationContext().getClass().getName());
		System.out.println(bean.getEnvironmentAware().getClass().getName());
		
		context.close();
	}
	
	@Test
	public void test20() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ListenerConfig.class);
		context.close();
	}
}
