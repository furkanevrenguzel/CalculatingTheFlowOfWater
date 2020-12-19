package project;

import java.util.concurrent.atomic.AtomicInteger;

public class Node {

    private static final AtomicInteger UID_SUPPLIER = new AtomicInteger(1);
	    private int num;  //Variables
	    private Double x;
	    private Double y;
	    private Double Q;
	    private Double h;

    public Node(Double x, Double y, Double Q) { //Node function
        this.num = UID_SUPPLIER.getAndIncrement();
        this.x = x;
        this.y = y;
        this.Q = Q;
    }

    public int getNum() 
    {
    	return num;
    }

    public double getX() 
    { 
    	return x;
    }

    public double getY() 
    { 
    	return y;
    }

    public double getQ() 
    { 
    	return Q; 
    }

    public Double getH() 
    { 
    	return h;
    }

    public void setH(Double h)
    {
    	this.h = h;
    }

    @Override
    public String toString() 
    {
        return "Node{" + "num=" + num +", x=" + x +", y=" + y + ", Q=" + Q +", h=" + h +'}';
    }
}
