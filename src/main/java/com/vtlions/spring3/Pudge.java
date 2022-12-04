package com.vtlions.spring3;

import org.springframework.stereotype.Component;

@Component
public class Pudge {

	private String name;
	private int level;
	private Item item;

	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Pudge [name=" + name + ", level=" + level + ", item=" + item + "]";
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;

	}

}
