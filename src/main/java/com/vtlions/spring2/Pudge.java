package com.vtlions.spring2;

public class Pudge implements Hero {

	private String name;
	private int level;
	private HeroItem item;

	
	
	
	public Pudge(String name, int level, HeroItem item) {
	
		this.name = name;
		this.level = level;
		this.item = item;
	}

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

	@Override
	public HeroItem getHeroItem() {
		// TODO Auto-generated method stub
		return item;
	}

	@Override
	public void setHeroItem(HeroItem item) {
		this.item = item;

	}

}
