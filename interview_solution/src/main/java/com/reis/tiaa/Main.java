package com.reis.tiaa;

import java.util.Scanner;

import com.reis.tiaa.executor.ProductBuilder;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Please enter raw Machiene  , Bolts and  seconds");
		
		Scanner scan = new Scanner(System.in);
		
		int machine  = Integer.parseInt(scan.next());
		
		int bolts = Integer.parseInt(scan.next());
		
		int assemblyTime = Integer.parseInt(scan.next());
		
		ProductBuilder builder = new ProductBuilder(machine, bolts, assemblyTime);
		
		builder.startWork();
		
	}

}
