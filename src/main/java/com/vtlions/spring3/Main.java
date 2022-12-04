package com.vtlions.spring3;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("context-file3.xml");

		System.out.println(context.getBean("dota2"));

	}

}
