package com.vtlions.spring;

public class XMLMessageProvider implements MessageProvider {

	@Override
	public String getMessage() {

		return "Some text from XML";
	}

}
