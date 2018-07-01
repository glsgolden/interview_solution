package com.reis.tiaa.model;

public class Bolt  implements Good {

	public String getName() {
		return Material.BOLT.toString();
	}

	public Material getType() {
		return Material.BOLT;
	}

}
