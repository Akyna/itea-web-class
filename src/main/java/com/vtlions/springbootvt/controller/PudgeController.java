package com.vtlions.springbootvt.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vtlions.springbootvt.model.Pudge;
import com.vtlions.springbootvt.service.PudgeService;

@RestController
@RequestMapping(value = "/pudgecontroller")
public class PudgeController {

	@Autowired
	PudgeService service;

	@RequestMapping(value = "/pudge/{id}", method = RequestMethod.GET)
	
	public Pudge getPudge(@PathVariable("id") int id) {
		System.out.println("ID="+id);
//		Pudge pudge = new Pudge(1, "Pudge1", 11);
		System.out.println("getPudge: " + service.getPudgeById(id));
//		return pudge;
		return service.getPudgeById(id);
	}

	@RequestMapping(value = "/pudge", method = RequestMethod.DELETE)
	public void delPudge(@RequestBody Pudge pudge) {

		System.out.println("delPudge: " + service.delPudge(pudge));
	}

	@RequestMapping(value = "/pudge", method = RequestMethod.PUT)
	public Pudge updatePudge(@RequestBody Pudge pudge) {
		System.out.println("before updatePudge: " + pudge);

		// pudge.setLevel(100500);
		// System.out.println("after updatePudge: " + pudge);
		return service.updatePudge(pudge);
	}

	@RequestMapping(value = "/pudge", method = RequestMethod.POST)
	public Pudge createPudge(@RequestBody Pudge pudge) {

		System.out.println("createPudge: " + pudge);
		return service.createPudge(pudge);
	}

}
