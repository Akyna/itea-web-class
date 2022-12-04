package com.vtlions.spring3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class Dota2 {
	
	

	public Dota2(Item bootsOfTravel) {

		this.bootsOfTravel = bootsOfTravel;
	}

	private final Item bootsOfTravel;

	@Override
	public String toString() {
		return "Dota2 [item=" + bootsOfTravel + "]";
	}

}
