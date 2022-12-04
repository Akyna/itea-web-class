package com.vtlions.spring2;

public class Item implements HeroItem {
	private String nameOfItem;

	public Item(String nameOfItem) {

		this.nameOfItem = nameOfItem;
	}

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
