/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

import java.util.Scanner;
/**
 *
 * @author i2wahid
 */
public class Main{
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	int n = input.nextInt(); //number of nodes
	int e = input.nextInt(); //number of edges
	//System.out.println("nodes: " + n + "\nedges: " + e); //checking if number of nodes and edges is stored correctly
		
	Graph graph = new Graph(n);
		
	// storing vertices that are connected by an edge:
	for(int i = 0; i < e; i++){
            int n1 = input.nextInt(); //input node 1
            int n2 = input.nextInt(); //input node 2
            graph.addEdge(n1, n2); // call the method to add edge to the graph
        }
	
        //test cases:
        System.out.println("The degree of vertex 0 is: " + graph.degreeVertex(0));
        graph.printAdjVertices(0);
                
        System.out.println("BFS: ");
	graph.BFS(0); //BFS with root 0 (starting from node 0)
		
	System.out.println("DFS: "); 
	graph.DFS(0); //DFS with root 0 (starting from node 0)
    }
}