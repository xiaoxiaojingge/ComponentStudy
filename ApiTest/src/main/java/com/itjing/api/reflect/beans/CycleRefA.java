package com.itjing.api.reflect.beans;

import lombok.Data;

@Data
public class CycleRefA {
    private CycleRefB cycleRefB;
}
