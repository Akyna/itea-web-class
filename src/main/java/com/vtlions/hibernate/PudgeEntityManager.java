package com.vtlions.hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PudgeEntityManager {
	EntityManager manager = Persistence.createEntityManagerFactory("dota2").createEntityManager();

	public Pudge add(Pudge pudge) {

		manager.getTransaction().begin();
		Pudge dbPudge = manager.merge(pudge);
		manager.getTransaction().commit();
		return dbPudge;
	}

	public void del(int id) {

		manager.getTransaction().begin();
		manager.remove(getPudgeById(id));
		manager.getTransaction().commit();
	}

	public Pudge getPudgeById(int id) {

		return manager.find(Pudge.class, id);
	}

	public Pudge update(Pudge pudge) {

		manager.getTransaction().begin();
		Pudge dbPudge = manager.merge(pudge);
		manager.getTransaction().commit();
		return dbPudge;
	}

	public List<Pudge> getAll() {
		TypedQuery<Pudge> namedQuery = manager.createNamedQuery("Pudge.getAll", Pudge.class);
		return namedQuery.getResultList();
	}
	
	public List<String> getAllName() {
		TypedQuery<String> namedQuery = manager.createNamedQuery("Pudge.getAll", String.class);
		return namedQuery.getResultList();
	}
}