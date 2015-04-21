package com.tracebucket.x.cqrs.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by ffl on 20-04-2015.
 */
public class NewCustomer {
	public final String firstName;
	public final String lastName;
	public Double point;

	private NewCustomer(){
		this.firstName = null;
		this.lastName = null;
		this.point = 0.0;
	}

	@JsonCreator
	public NewCustomer(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("point") Double point){
		this.firstName = firstName;
		this.lastName = lastName;
		this.point = point;
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
