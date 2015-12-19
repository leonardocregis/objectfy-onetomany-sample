package com.google.devrel.training.conference.service;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
class Inside{
	@Id
	public Long id;

	@Override
	public String toString() {
		return "Inside [id=" + id + "]";
	}
	
}
