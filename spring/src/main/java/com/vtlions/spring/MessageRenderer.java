package com.vtlions.spring;

public interface MessageRenderer {

	void setMessageProvider(MessageProvider messageProvider);
	
	void render();
	
}
