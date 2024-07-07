package com.itjing.api.reflect.beans;

import lombok.Data;

@Data
public class CycleRefC {

	private CycleRefA cycleRefA;

}
