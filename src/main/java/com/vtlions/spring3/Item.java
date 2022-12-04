package com.vtlions.spring3;

import org.springframework.stereotype.Component;

@Component
public class Item {
	private String nameOfItem ="Some Item";

	public void setNameOfItem(String nameOfItem) {
		this.nameOfItem = nameOfItem;
	}

	public String getNameOfItem() {
		return nameOfItem;
	}

	@Override
	public String toString() {
		return "Item [nameOfItem=" + nameOfItem + "]";
	}

}
