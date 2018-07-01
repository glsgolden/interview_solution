package com.reis.tiaa.executor;

import java.util.Objects;
import java.util.concurrent.Callable;

import com.reis.tiaa.model.ConveyorBelt;
import com.reis.tiaa.model.Employee;
import com.reis.tiaa.model.Good;
import com.reis.tiaa.model.Material;

public class Worker implements Callable<Integer>
{
	
	private Employee employee;
	private Good machine;
	private Good bolt1;
	private Good bolt2;
	private ConveyorBelt conveyorBelt;
	private final int assemblyTime;

	private int totalAssembled;
	
	public Worker(Employee employee, ConveyorBelt belt, int assemblyTime)
	{
		this.employee = employee;
		this.conveyorBelt = belt;
		this.assemblyTime = assemblyTime;
	}

	private void pickupRawMaterial() {
		if ((totalAssembled > 0) && (conveyorBelt.getNumberOfProducts() < 3) ) {
			return;
		}

		final Good rawMaterial = conveyorBelt.getProduct();
		
		if(Objects.isNull(machine) && rawMaterial.getType().equals(Material.MACHINE))
		{
			machine = rawMaterial;
		}
		else if(Objects.isNull(bolt1) && rawMaterial.getType().equals(Material.BOLT))
		{
			bolt1 = rawMaterial;
		}
		else if(Objects.isNull(bolt2) && rawMaterial.getType().equals(Material.BOLT))
		{
			bolt2 = rawMaterial;
		}
		else {
			conveyorBelt.addProduct(rawMaterial);
		}
		
		
	}

	private void assemble() {
		try {
			bolt1 = null;
			bolt2 = null;
			machine = null;
			Thread.sleep((long) assemblyTime * 1000);
			totalAssembled++;
		} catch (final InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public boolean isAllMaterial()
	{
		return !Objects.isNull(machine) && !Objects.isNull(bolt1) && !Objects.isNull(bolt2) ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public Integer call() throws Exception {
		
		System.out.println("Employee =" + employee.getEmpName() + " Building a product" );
		
		while (conveyorBelt.isHavingProduct()) {
			pickupRawMaterial();

			if (isAllMaterial()) {
				assemble();
			}
		}
		return totalAssembled;
	}
	
}
