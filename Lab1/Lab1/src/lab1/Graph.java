/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;
import java.util.*;

/**
 *
 * @author i2wahid
 */
public class Graph{
    //instance variables:
    private int n; //number of nodes
    private ArrayList<ArrayList<Integer>> adjList; //adjacent linked list (each element of the adjList stores an Integer ArrayList)
    
    //constructor:
    public Graph(int n){
        this.n = n;
        adjList = new ArrayList<>();
        
        for(int i = 0; i < n ; i++){ // the length of the outter ArrayList is equal to the number of nodes
            adjList.add(new ArrayList<Integer>()); //adding elements to the adjList; each element stores an Integer ArrayList (linked list)
        }
    }
    
    public void addEdge(int a, int b){
        (adjList.get(a)).add(b); //add b into the linked list that stores all adjacent vertices of a
        (adjList.get(b)).add(a); //and vice versa, since it's an undirected graph
    }
    
    //returns the number of neighbours for a vertex a
    public int degreeVertex(int a){
        return (adjList.get(a)).size(); //returns the size of the LinkedList stored in the adjList at index a (which is, essentially, the number of neighburs of a)
    }
    
    //Print the adjacent vertices of a vertex a
    public void printAdjVertices(int a){
        System.out.print("The adjacent vertices of the vertex " + a + " are: ");
        for(int i : adjList.get(a)){ //using enhanced for-loop
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public void BFS(int start){
        boolean [] visited = new boolean [n]; //stores whether the nodes are visited
        ArrayList <Integer> queue = new ArrayList <Integer>(); //stores the visited nodes
        visited[start] = true; //the start node is obviously visited
        queue.add(start); //add the start node to the ArrayList queue, since it's visited
        
        while(!queue.isEmpty()){
            int current = queue.get(0); //make 'current' equal to the first value (element zero) of the queue ArrayList
            queue.remove(0); //and then remove that (first) value from the ArrayList queue
            
            System.out.print(current + " ");
            
            for(int i : adjList.get(current)){ //go through all the neighbours(i) of current
                if(!visited[i]){ //if any neighbour of current is not visited 
                    //visit that node:
                    visited[i] = true; //node's visited status is changed to true
                    queue.add(i); //add that
                }
            }
            
        }
        System.out.println();
    }
    
    public void DFS(int start){
        boolean [] visited = new boolean [n]; //array storing status of each node
        DFSRec(start, visited);
    }
    
    public void DFSRec(int current, boolean[] visited){
        visited[current] = true; //update status of current node being visited
        System.out.print(current + " "); //output node visited
        
        for(int i : adjList.get(current)){ //go through all the neighbours(i) of current
            if(!visited[i]){
                DFSRec(i, visited); //recursive call to DFSRec to continue a single path
            }
        }
    }
}