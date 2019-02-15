package cn.itcast.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class ProfileConfig {
	@Profile("test")
	@Bean("testDataSource")
	public DataSource testDataSource() {
		// 使用 <name-config name="test">  部分的配置
		return new ComboPooledDataSource("test");
	}
	
	@Profile("dev")
	@Bean("devDataSource")
	public DataSource devDataSource() {
		// 无参构造就是使用 <default-config> 的配置
		return new ComboPooledDataSource();
	}
	
	@Profile("produce")
	@Bean("produceDataSource")
	public DataSource produceDataSource() {
		// 使用 <name-config name="produce">  部分的配置
		return new ComboPooledDataSource("produce");
	}
}
