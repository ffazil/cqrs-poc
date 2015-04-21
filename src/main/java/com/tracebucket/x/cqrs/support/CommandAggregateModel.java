package com.tracebucket.x.cqrs.support;

import com.tracebucket.x.cqrs.command.Command;

import java.io.Serializable;

/**
 * Created by ffl on 20-04-2015.
 */
public class CommandAggregateModel implements Serializable{
	private Command command;

}
