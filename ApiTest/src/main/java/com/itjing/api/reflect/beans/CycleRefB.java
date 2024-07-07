package com.itjing.api.reflect.beans;

import lombok.Data;

@Data
public class CycleRefB {

	private CycleRefC cycleRefC;

}
