package com.vtlions.spring2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		 ApplicationContext context = new ClassPathXmlApplicationContext("context-file.xml");

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//		context.register(Dota2Configuration.class, ItemConfiguration.class);
//		context.refresh();

		System.out.println(context.getBean("pudge"));
//		System.out.println("\n");
//		System.out.println(context.getBean(Pudge.class));
		//context.close();
	}

}
