package com.itjing.api.deginmodel.brige;

public abstract class Operator {

	protected ScheduleBrigeAdapter schedule;

	public Operator(ScheduleBrigeAdapter schedule) {
		this.schedule = schedule;
	}

	public abstract void done();

}
