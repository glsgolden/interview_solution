package com.reis.tiaa.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.reis.tiaa.model.Bolt;
import com.reis.tiaa.model.ConveyorBelt;
import com.reis.tiaa.model.Employee;
import com.reis.tiaa.model.Machine;

public class ProductBuilder {

	private int machine;
	private int bolt;
	private int assemblyTime;
	private ConveyorBelt conveyorBelt;
	private int totalProduct = 0;
	long totalTimeTaken = 0;

	public ProductBuilder(int machine, int bolts, int assemblyTime) {
		this.machine = machine;
		this.bolt = bolts;
		this.assemblyTime = assemblyTime;
		initializeConveyBelts();
	}

	private List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		Employee e = new Employee("Employee1");
		employees.add(e);

		e = new Employee("Employee2");
		employees.add(e);
		e = new Employee("Employee3");
		employees.add(e);
		
		return employees;

	}

	private void initializeConveyBelts() {
		conveyorBelt = new ConveyorBelt();
		int temp = this.machine;
		for (int prodcutWalker = 0; prodcutWalker < this.bolt; prodcutWalker++) {
			conveyorBelt.addProduct(new Bolt());
			if (temp-- > 0) {
				this.conveyorBelt.addProduct(new Machine());
			}

		}

	}
	
	public void startWork() {
		
		
		if (assemblyTime < 1) {
			throw new IllegalArgumentException("Assembly time must be greater than 0.");
		}

		if ((machine < 1) || (bolt < 1)) {
			throw new IllegalArgumentException("Number of machines and bolts must be greater than 0.");
		}

		if ((machine > 0) && (bolt > 0) && ((machine * 2) != bolt)) {
			throw new IllegalArgumentException("Number of bolts must be double of number of machines.");
		}
		
		
		final ExecutorService executor = Executors.newFixedThreadPool(3);
		final List<Future<Integer>> workerList = new ArrayList<>();
		for (Employee employee : getEmployees()) {
			final Future<Integer> future = executor.submit(new Worker(employee, conveyorBelt, assemblyTime));
			workerList.add(future);
		}

		for (final Future<Integer> w : workerList) {
			try {
				totalProduct += w.get();
			} catch (InterruptedException | ExecutionException e) {
				Thread.currentThread().interrupt();
			}
		}
		executor.shutdown();

		System.out.println("Total Products = " + totalProduct);

		totalTimeTaken = ((totalProduct / 3) * assemblyTime);
		if((totalProduct % 3) > 0) {
			totalTimeTaken += assemblyTime;
		}
		System.out.println("Total Time Taken = " + totalTimeTaken);
	}
	
	public int getTotalProduct() {
		return totalProduct;
	}

	public long getTotalTimeTaken() {
		return totalTimeTaken;
	}

}
