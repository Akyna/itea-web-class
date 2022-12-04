package com.vtlions.spring3;

import org.springframework.stereotype.Component;

@Component
public class BootsOfTravel extends Item{

	
	@Override
	public String getNameOfItem() {
		
		return "Boots of travel";
	}
}
