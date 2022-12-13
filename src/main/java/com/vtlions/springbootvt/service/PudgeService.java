package com.vtlions.springbootvt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vtlions.springbootvt.model.Pudge;
import com.vtlions.springbootvt.repository.PudgeRepository;

@Service
public class PudgeService {

	@Autowired
	private PudgeRepository repository;

	public List<Pudge> list() {
		return repository.findAll();
	}

	public Pudge delPudge(Pudge pudge) {
		repository.delete(pudge);
		return pudge;
	}

	public Pudge createPudge(Pudge pudge) {
		return repository.save(pudge);
	}

	public Pudge updatePudge(Pudge pudge) {

		return repository.saveAndFlush(pudge);
	}

	public Pudge getPudgeById(int id) {
		return repository.findById(id).get();

	}
}
