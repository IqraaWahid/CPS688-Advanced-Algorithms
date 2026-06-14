/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Problem2 {
    public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
		
	int n = input.nextInt(); //number of nodes in graph
	int e = input.nextInt(); //number of edges in graph
		
	//adjacency linked lists
	ArrayList <ArrayList<Integer>> graph = new ArrayList<>();
	ArrayList <ArrayList<Integer>> reverseGraph = new ArrayList<>();
		
	for(int i = 0; i < n; i++){
            //extending the adjacency linked list's outter arrayList length equal to the number of nodes
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
	}
		
	//add the edges into the graph
	for(int i = 0; i < e ; i++){
            int from = input.nextInt();
            int to = input.nextInt();
		    
            graph.get(from).add(to);
            reverseGraph.get(to).add(from);
        }
		
	if(isStronglyConnected(graph, reverseGraph, n)){
            System.out.println("yes");
	}
		
	else{
            System.out.println("no");
	}
    }
	
    public static boolean isStronglyConnected(ArrayList<ArrayList<Integer>> graph, ArrayList<ArrayList<Integer>> reverseGraph, int n) {
        boolean [] visited = new boolean [n];
	    
        dfs(0, graph, visited); //perform dfs starting at node 0
	    
	//check if all nodes were visited after performing dfs on graph starting from node 0
	for(int i=0; i<n; i++){
	    if(!visited[i]){
	        return false; //if any node in 'graph' not visited after performing DFS on graph, then return false
	    }
	}
	    
	visited = new boolean[n]; //Mark all vertices as not-visited in reversed graph
	dfs(0, reverseGraph, visited); //perform dfs on reversed graph starting at node 0
	    
	//check if node 0 is reachable from any other node
	//since node 0 can reach every other node in the reversed graph, every other node in the original graph can reach node 0
	for(int i = 0; i<n; i++){
	    if(!visited[i]){
	        return false;
	    }
	}
	return true; //graph is strongly connected: every node is reachable from every other node
    }
	
    //dfs on graphs
    public static void dfs(int current, ArrayList<ArrayList<Integer>> graph, boolean [] visited){
	visited[current] = true;
	    
	for(int n : graph.get(current)){
	    if(!visited[n]){
	        dfs(n, graph, visited);
	    }
	}
    }
}
