package cn.itcast.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

import cn.itcast.entity.Teacher;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 注意： include 需要修改默认的过滤规则， 而exclude 不需要修改默认的过滤规则
@ComponentScan(
				basePackages="cn.itcast",
				includeFilters= {
						   			@Filter(type=FilterType.ANNOTATION, classes= {Controller.class}),
								    @Filter(type=FilterType.ASSIGNABLE_TYPE, classes= {Teacher.class})
				                },
				useDefaultFilters=false
		      )
@Configuration
public class ControllerConfig {}
