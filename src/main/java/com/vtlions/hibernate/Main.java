package com.vtlions.hibernate;

public class Main {

	public static void main(String[] args) {

		PudgeEntityManager manager = new PudgeEntityManager();
		Pudge pudge = new Pudge();
		pudge.setName("Some Pudge");
		pudge.setLevel(13);
		pudge.setId(3);
		pudge.setItem(new Item().setItemName("Boots of travel-222222").setStrength(777));
	//	System.out.println(manager.add(pudge));
//
//		manager.del(1);
//		System.out.println(manager.getPudgeById(3));
//		System.out.println(manager.getPudgeById(1));

//		Pudge pudge3 = manager.getPudgeById(3);
//		pudge3.setName("Pudge 3");
//		manager.update(pudge3);
	//	System.out.println(manager.getAll()+"\n");
		System.out.println(manager.getAllName()+"\n");
		
		
	}
}
