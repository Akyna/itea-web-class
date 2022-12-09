package com.vtlions.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String itemName;
	private int strength;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public Item setItemName(String itemName) {
		this.itemName = itemName;
		return this;
	}

	public int getStrength() {
		return strength;
	}

	public Item setStrength(int strength) {
		this.strength = strength;
		return this;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", strength=" + strength + "]";
	}

}
