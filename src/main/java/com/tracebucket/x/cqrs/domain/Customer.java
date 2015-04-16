package com.tracebucket.x.cqrs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ffl on 16-04-2015.
 */
public class Customer {
	private String firstName;
	private String lastName;
	private Double point = 0.0;

	private Customer(){

	}

	@JsonCreator
	public Customer(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("point") Double point){
		this.firstName = firstName;
		this.lastName = lastName;
		this.point = point;
	}

	public Customer incrementPoint(Double increment){
		point = point + increment;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Double getPoint() {
		return point;
	}
}
