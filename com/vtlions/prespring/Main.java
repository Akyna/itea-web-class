package com.vtlions.prespring;

public class Main {

	public static void main(String[] args) {

		ApplicationSupportFactory factory = ApplicationSupportFactory.getInstance();

		MessageProvider provider = factory.getMessageProvider();

		MessageRenderer renderer = factory.getMessageRenderer();

		renderer.setMessageProvider(provider);
		renderer.render();

	}

}
