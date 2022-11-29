package com.vtlions.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainSpring {

	public static void main(String[] args) {
		
		
		ApplicationContext context=new ClassPathXmlApplicationContext("app-context.xml");
		//MessageProvider provider = (MessageProvider) context.getBean("messageProvider");
		MessageRenderer renderer = (MessageRenderer) context.getBean("messageRenderer");
		
		//renderer.setMessageProvider(provider);
		renderer.render();
		
	}

}
