package com.reis.tiaa.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConveyorBelt {

    private BlockingQueue<Good> belt= new ArrayBlockingQueue<>(15);

    
    public void addProduct(Good product)
    {
    	this.belt.add(product);
    }
    
    public Good getProduct()
    {
    	return belt.poll();
    }
    
    public Boolean isHavingProduct()
    {
    	return (belt.isEmpty() ? Boolean.FALSE : Boolean.TRUE);
    }

    public int getNumberOfProducts()
    {
    	return belt.size();
    }
    

}
