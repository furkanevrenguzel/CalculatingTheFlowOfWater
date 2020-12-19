package project;

import java.util.concurrent.atomic.AtomicInteger;

public class Tube {

    private static final AtomicInteger UID_SUPPLIER = new AtomicInteger(1);
    private int num;	//Variables
    private Node nodeA;
    private Node nodeB;
    private double diameter;
    private double length;
    private double B;
    private double q;

    public Tube(Node nodeA, Node nodeB, double diameter) {
        this.num = UID_SUPPLIER.getAndIncrement();
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.diameter = diameter;
        this.length = calcLength(this.nodeA, this.nodeB);
        this.B = calcB();
    }

    public Node getNodeA() 
    {
        return nodeA;
    }

    public Node getNodeB() 
    {
        return nodeB;
    }

    public double getB() { return B; }

    public void setq(double q) { this.q = q; }

    
    double calcLength(Node A, Node B) { // Calculating height
        return Math.hypot(A.getX() - B.getX(), A.getY() - B.getY());
    }

   
    double calcB() // Calculating permeability
    { 
        final double g = 9.81;
        final double viscosity = 1 * Math.pow(10,-6);
        return (Math.PI * g * Math.pow(diameter,4)) / (128 * viscosity * length);
    }

    @Override
    public String toString() {
        return "Tube{" +"num=" + num +", nodeA=" + nodeA +", nodeB=" + nodeB +", diameter=" + diameter +", length=" + length +", B=" + B +", q=" + q +'}' + "\n";
    }
}
