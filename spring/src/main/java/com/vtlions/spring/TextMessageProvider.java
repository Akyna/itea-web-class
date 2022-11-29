package com.vtlions.spring;

public class TextMessageProvider implements MessageProvider{

	@Override
	public String getMessage() {
		
		return "Some text";
	}

	
	
}
