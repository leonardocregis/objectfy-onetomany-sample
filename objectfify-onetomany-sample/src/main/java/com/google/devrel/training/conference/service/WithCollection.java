package com.google.devrel.training.conference.service;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class WithCollection {
	@Id
	public Long id;

	@Index
	private List<Key<Inside>> stuff = new ArrayList<>();
	
	@Index
	private List<String> beum = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getBeum() {
		return beum;
	}

	public void setBeum(List<String> beum) {
		this.beum = beum;
	}

	public List<Key<Inside>> getStuff() {
		return stuff;
	}

	public void setStuff(List<Key<Inside>> stuff) {
		this.stuff = stuff;
	}

	@Override
	public String toString() {
		return "WithCollection [id=" + id + ", stuff=" + stuff + "]";
	}
}