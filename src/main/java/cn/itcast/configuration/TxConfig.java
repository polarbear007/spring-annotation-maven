package cn.itcast.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import cn.itcast.mapper.StudentMapper;
// 包扫描我们只让扫描 service 包就好了， dao 层的不用扫， controller 层的有springmvc 容器扫描，所以也不需要扫
@ComponentScan(basePackages="cn.itcast.service")
@EnableTransactionManagement  // 开启事务注解配置，使 @Transactional 注解生效
@Configuration
public class TxConfig {
	
	// 配置 dataSource
	@Bean
	public DataSource dataSource() throws Exception {
		// 如果我们没有配置 c3p0-config.xml 的话，那么可以使用代码的方式来填写数据库的必要参数
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spring_annotation?serverTimezone=Asia/Shanghai&useSSL=false");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		return  dataSource;
	}
	
	// 必须配置事务管理器
	@Bean
	public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	// 配置 StudentDao 对象，因为我们继承了  jdbcDaoSupport ，无法使用 @Repository 注解
	// 这个 jdbcDaoSupport 提供了  setDataSource 方法，但是没有@Autowired 注解，我们需要自己手动配置
	// 如果我们整合 mybatis 的话，那么我们也就不需要继承 jdbcDaoSupport 了， 直接使用 mapper 代理开发
	@Bean
	public StudentMapper studentMapper(@Autowired DataSource dataSource) {
		StudentMapper studentMapper = new StudentMapper();
		studentMapper.setDataSource(dataSource);
		return studentMapper;
	}
}
