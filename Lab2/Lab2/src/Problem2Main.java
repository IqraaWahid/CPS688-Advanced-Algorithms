/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */

import java.util.Scanner;

public class Problem2Main{
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
		
	int n = input.nextInt(); //number of nodes
	int e = input.nextInt(); //number of edges
		
	Problem2Graph g = new Problem2Graph (n); //creating an object of the Graph class
		
	//add all edges to the graph
	for(int i = 0; i < e ; i++){
            int n1 = input.nextInt(); //user input node 1
            int n2 = input.nextInt(); //user input node 2
            g.addEdge(n1, n2); //adds edge between nodes 1 and 2 onto the graph
        }
		
	/*
	check for cycle
        - if cycle detected: print "no" to console
        - else print "yes"
        */
        if(g.hasCycle()){
            System.out.println("no");
        }
	else{
            System.out.println("yes");
	}
    }
}
