package com.reis.tiaa.model;

public class Machine implements Good {

	public String getName() {
		return Material.MACHINE.toString();
	}

	public Material getType() {
		return Material.MACHINE;
	}
}
