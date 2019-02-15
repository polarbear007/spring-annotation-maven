package cn.itcast.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:db.properties")
@PropertySource("classpath:position.properties")
public class AwareConfig implements EmbeddedValueResolverAware{
	private StringValueResolver resolver;
	@Value("${jdbc.driverClass}")
	private String driverClass;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	
	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.resolver = resolver;
	}
	
	@Bean("testDataSource")
	public DataSource testDataSource() throws Exception {
		ComboPooledDataSource testDataSource = new ComboPooledDataSource();
		testDataSource.setDriverClass(driverClass);
		testDataSource.setUser(user);
		testDataSource.setPassword(password);
		
		// 获取特定的 url 
		String jdbcUrl = resolver.resolveStringValue("${test.jdbc.jdbcUrl}");
		testDataSource.setJdbcUrl(jdbcUrl);
		return testDataSource;
	}
	
	@Bean("devDataSource")
	public DataSource devDataSource() throws Exception {
		ComboPooledDataSource devDataSource = new ComboPooledDataSource();
		devDataSource.setDriverClass(driverClass);
		devDataSource.setUser(user);
		devDataSource.setPassword(password);
		
		// 获取特定的 url 
		String jdbcUrl = resolver.resolveStringValue("${dev.jdbc.jdbcUrl}");
		devDataSource.setJdbcUrl(jdbcUrl);
		return devDataSource;
	}
	
	@Bean("produceDataSource")
	public DataSource produceDataSource() throws Exception {
		ComboPooledDataSource produceDataSource = new ComboPooledDataSource();
		produceDataSource.setDriverClass(driverClass);
		produceDataSource.setUser(user);
		produceDataSource.setPassword(password);
		
		// 获取特定的 url 
		String jdbcUrl = resolver.resolveStringValue("${produce.jdbc.jdbcUrl}");
		produceDataSource.setJdbcUrl(jdbcUrl);
		return produceDataSource;
	}

	public StringValueResolver getResolver() {
		return resolver;
	}

	public void setResolver(StringValueResolver resolver) {
		this.resolver = resolver;
	}
	
	
}
