/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */
import java.util.Scanner;

public class Problem3{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt(); //user input number of nodes of graph
        int e = input.nextInt(); //user input number of edges of graph
        
        int [][] graph = new int [n][n]; //adjancency matrix
        
        for(int i = 0 ; i < e ; i++){
            int n1 = input.nextInt(); //a node
            int n2 = input.nextInt(); //another node connected to n1 with an edge
            int w = input.nextInt(); //weight of the edge
            //fill the adjancency matrix appropriate weights
            graph[n1][n2] = w;
            graph[n2][n1] = w; //since undirected graph
	}
		
	boolean [] visited = new boolean[n]; //nodes already in the MST
	visited[2] = true; //starting vertex, already in the MST
	int total = 0; //stores the MST sum of edges
		
	for(int edges = 0; edges < n-1; edges++){ //for MST we need n-1 edges
            int min = Integer.MAX_VALUE; //min is infinite initially
            int from = -1;
            int to = -1;
            
            //checks all nodes, whether they're visited meaning in the MST(MST expands from visited nodes), if yes then expand from there
            for(int i = 0; i < n; i++){
		//only expand from nodes already in MST (the visited array)
		if(visited[i]){//*we look at all node's in the MST's adjacent and choose the smallest edge, whos "to" node is added to the MST
		    //check all neighbours of i
		    for(int j = 0; j < n; j++){
		        /*
		        - make sure the node adjacent to i is not in the MST already (this will help prevent cycles)
		        - make sure the edge is not zero, otherwise, it's not an edge
		        - make sure the node is weight of the edge is smaller than the minimum already stored
		        */
		        if(!visited[j] && graph[i][j] != 0 && graph[i][j] < min){
		            //these values update until all nodes adjacent to i are gone through to fine the most smallest edge
		            min = graph[i][j]; //update minimum's value to the smallest adjacent edge to i
		            from = i;
		            to = j;
		        }
		    }
		}
            }
            visited[to] = true; //adds the correct node to the MST
            total = total + min; //update the value of the MST total weight
		    
            System.out.println("Edge " + from + "-" + to + " has a weight of " + min);
	}
	System.out.println("MST = " + total);
    }
}