package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PipeNet {

    List<Node> nodes = new ArrayList<>();
    List<Tube> tubes = new ArrayList<>();
    static List<Double> data = new ArrayList<>();
    private final double n_nodes;
    private final double n_tubes;

    public PipeNet() { //Creating data for PipeNet
        getData();
        this.n_nodes = data.get(0);
        this.n_tubes = data.get(1);
        getNodes();
        getTubes();
        getSolve();
        calcFlux();
    }
 
    public List<Tube> GetTubes() { return tubes; }

    
    public static void getData() {

        File file = new File("Data.txt"); // From "Data.txt"

        try(Scanner reader = new Scanner(file)) {
            while(reader.hasNextDouble()) {
                Double a = reader.nextDouble();
                data.add(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }

    
    public void getNodes() { // Getting nodes from arraylist
        for(int i = 2; i < n_nodes * 3; i +=3) 
        {
            int vertex = i;
            nodes.add(new Node(data.get(vertex), data.get(++vertex), data.get(++vertex)));
        }
    }


    public void getTubes() {
         for(int i = (int) (n_nodes * 3) + 2; i < data.size(); i += 3)
         {
            int vertex = i;
            tubes.add(new Tube(nodes.get((int) (data.get(vertex)-1)), nodes.get((int) (data.get(++vertex)-1)), data.get(++vertex)));
         }
    }

    
    public void fillArray(double[][] B, double[] Q) { // Inflow-Outflow matrix

        for(int i = 0; i < nodes.size(); i++)
        {
            for(int j = 0; j < tubes.size(); j++)
            {
                B[tubes.get(j).getNodeA().getNum() -1][tubes.get(j).getNodeB().getNum() -1] = -tubes.get(j).getB();
                B[tubes.get(j).getNodeB().getNum() -1][tubes.get(j).getNodeA().getNum() -1] = -tubes.get(j).getB();
                if(nodes.get(i).equals(tubes.get(j).getNodeA()) || nodes.get(i).equals(tubes.get(j).getNodeB()))
                    B[nodes.get(i).getNum() -1][nodes.get(i).getNum()-1] += tubes.get(j).getB();
            }
        }

        for(int i  = 0; i < nodes.size(); i++)
        {
            Q[i] = -nodes.get(i).getQ();
        }
    }

   
   public void getSolve() { // Help of Gaussian Elimination

        double[][] B = new double[nodes.size()][nodes.size()];
        double[] Q = new double[nodes.size()];
        double[] h_Of_Nodes;
        fillArray(B,Q);

        h_Of_Nodes = GaussElim.solve(B,Q);

       for(int i = 0; i < nodes.size(); i++)
           nodes.get(i).setH(h_Of_Nodes[i]);
    }

    
    public void calcFlux() { // Calculating flux
        for(int i = 0; i < tubes.size(); i++)
        {
            tubes.get(i).setq(tubes.get(i).getB() * (tubes.get(i).getNodeA().getH() - tubes.get(i).getNodeB().getH()));
    
        }
    }
}
