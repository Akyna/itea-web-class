package com.vtlions.spring2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Dota2Configuration {

	@Bean
	public Item getItem() {
		Item item = new Item();
		item.setNameOfItem("Boots of travel");

		return item;
	}

	@Bean
	public Pudge getPudge() {
		Pudge pudge = new Pudge();
		pudge.setName("Pudge");
		pudge.setHeroItem(getItem());
		pudge.setLevel(13);

		return pudge;
	}

}
