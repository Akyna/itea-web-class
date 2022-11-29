package com.vtlions.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ApplicationSupportFactory {

	private static ApplicationSupportFactory instance;
	private MessageProvider messageProvider;
	private MessageRenderer messageRenderer;

	private ApplicationSupportFactory() {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(
					"C:/Users/vt1/eclipse-workspace/WebLessons/src/com/vtlions/prespring/application.properties")));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		String provider=properties.getProperty("provider");
		
		String renderer=properties.getProperty("renderer");
		
		try {
			messageProvider=(MessageProvider) Class.forName(provider).getDeclaredConstructor().newInstance();
			messageRenderer=(MessageRenderer) Class.forName(renderer).getDeclaredConstructor().newInstance();
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ApplicationSupportFactory getInstance() {

		if (instance == null) {
			instance = new ApplicationSupportFactory();
		}

		return instance;
	}

	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

	public MessageRenderer getMessageRenderer() {
		return messageRenderer;
	}

	// C:\Users\vt1\eclipse-workspace\WebLessons\src\com\vtlions\prespring\application.properties

}
