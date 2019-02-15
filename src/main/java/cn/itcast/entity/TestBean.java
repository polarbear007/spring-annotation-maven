package cn.itcast.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;

public class TestBean {
	@Autowired
	private EnvironmentAware  environmentAware;
	
	@Autowired
	private ApplicationContext applicationContext;

	public EnvironmentAware getEnvironmentAware() {
		return environmentAware;
	}

	public void setEnvironmentAware(EnvironmentAware environmentAware) {
		this.environmentAware = environmentAware;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	
}
