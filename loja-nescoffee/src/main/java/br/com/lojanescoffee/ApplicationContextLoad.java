package br.com.lojanescoffee;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public abstract class ApplicationContextLoad implements ApplicationContextAware{

	@Autowired
	private static ApplicationContext applicationContext;
	
	//@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		this.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
}
