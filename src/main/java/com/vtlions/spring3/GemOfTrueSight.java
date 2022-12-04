package com.vtlions.spring3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
public class GemOfTrueSight extends Item {

	@Override
	public String getNameOfItem() {

		return "Gem of true sight";
	}
}
