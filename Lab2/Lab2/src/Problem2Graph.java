/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.ArrayList;

public class Problem2Graph{
    //instance variables:
    private int n;
    private ArrayList<ArrayList<Integer>> adjList;
    
    //constructor:
    public Problem2Graph(int n){
        //set initial values to the instance variables:
        this.n = n;
        adjList = new ArrayList<>();
        
        //make the adjList size = n
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<Integer>());
        }
    }
    
    //method to add edge to the adjList
    public void addEdge(int a, int b){
        adjList.get(a).add(b);
        adjList.get(b).add(a);
    }
    
    //method that checks for a cycle in the graph:
    public boolean hasCycle(){
        boolean [] visited = new boolean [n]; //array that stores visited status of each node
        
        //in case graph is disconnected: we have to do DFS and check for cycles there as well
        for(int i = 0; i<n; i++){
            if(!visited[i]){//starts DFS at node 0
                //start DFS from unvisited node:
                //-1 means the node has no parent
                if(DFSRec(i, visited, -1)){//zero don't have parent
                    return true; //cycle detected
                }
            }
        }
        return false; //no cycle detected
    }
    
    public boolean DFSRec(int current, boolean [] visited, int parent){
        visited[current]=true; //obviously the starting node is visited
        
        //go through all the neighbours of current node
        for(int i : adjList.get(current)){
            if(!visited[i]){//if any of the neighbours are unvisited
                if(DFSRec(i, visited, current)){//recursion used to go from one node to a new unvisited one from the starting
                    return true; //there is a cycle, returns true for DFSRec()
                }
            }
            
            else if(i != parent){//i is visited and is not the parent (not parent because it is an undirected graph so always parents would be adjacent)
                return true; //there is a cycle, returns true to DFSRec(i, visited, current) - line 49
            }
        }
        
        return false;//otherwise no cycle
    }
}