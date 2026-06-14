/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */
//Note: after inputing the matrix, click keyboard shortcuts to reach EOF so proper outputs can be outputted
import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main (String [] args){
        Scanner input = new Scanner (System.in);
        
        ArrayList <ArrayList<Integer>> problem = new ArrayList<>(); //will store the input table
        /*
        Table format:
        - each row in the input table represents a job applicant
        - each column in the input table represents a job
        - 1 in the table means that the job applicant wants this job and 0 means they don't
        */
        
        //press Ctrl + D to reach EOF
        while(input.hasNextLine()){
            String line = input.nextLine(); //store one row from the input
            
            //if inputted string is empty, then ignore that string
            if(line.length() == 0){
                continue;
            }
            
            /*split the string into integers so they can be stored as an element of the adjacency linked list
            input: "1 1 0 1" becomes ["1", "1", "0", "1"]
            */
            String [] num = line.split(" ");
            
            ArrayList <Integer> row = new ArrayList <>(); //will store a row of the inputted matrix
            
            for (int i = 0; i<num.length; i++){
                row.add(Integer.parseInt(num[i])); //adding the inputted rows into the ArrayList called row, in order
            }
            
            problem.add(row); //add the row into the matrix called problem
        }
        
        //after the matrix has been completely stored into the ArrayList 'problem', no user input accpeted after...
        input.close();
        
        int applicants = problem.size(); //number of applicants is equal to the number of rows
        int jobs = problem.get(0).size(); //number of jobs is equal to the number of columns
        
        //convert the adjacency linked list 'problem' into an adjacency matrix (I solved it this way - makes it easier to understand what's happening)
        int [][] matrix = new int [applicants][jobs];
        
        //copy all contents of problem ArrayList into matrix Matrix:
        for(int i = 0; i<applicants; i++){
            for(int j=0; j<jobs; j++){
                matrix[i][j]=problem.get(i).get(j);
            }
        }
        
        int answer = maxBipartiteMatching(matrix, applicants, jobs); //stores the number of maximum matches possible
        
        System.out.println("The maximum number of applicants matching for the jobs is " + answer);
    }
    
    public static int maxBipartiteMatching(int [][] matrix, int applicants, int jobs){
        //converting the matrix table into a network flow:
        int source = 0; //source is node 0
        int firstApplicant = source + 1; //comes right after the source
        int firstJob = applicants + firstApplicant;
        int target = firstJob + jobs;
        int totalNodes = jobs + applicants + 2; //total Nodes = jobs + applicants + source + target
        
        int [][] capacity = new int [totalNodes][totalNodes];
        //fill up the capacity array:
        //the capacity from source to each applicant is 1
        for(int i=0; i<applicants; i++){
            capacity[source][firstApplicant + i] = 1; //directed graph hence won't do other way around
        }
        
        for(int i=0;i<applicants; i++){
            for(int j=0; j<jobs; j++){
                if(matrix[i][j]==1){ //if applicant i is intrested in job j, then set the weight of the corresponding edge (i --> j) to 1
                    capacity[firstApplicant + i][firstJob + j]=1;
                }
            }
        }
        
        //from each job to the target the capacity is 1:
        for(int i=0; i<jobs; i++){
            capacity[firstJob + i][target] = 1;
        }
        
        //use the Ford Fulkerson algorithm to determine the max. flow
        //apply this algorithm on the network flow built
        return fordFulkerson(capacity, source, target, totalNodes);
    }
    
    public static int fordFulkerson(int [][] capacity, int source, int target, int totalNodes){
        int [][] residual = new int [totalNodes][totalNodes]; //stores the network flow but updated (changes of the fordfulkerson algorithm stored here)
        
        //copy capacity array into the residual array
        for(int i=0; i<totalNodes; i++){
            for(int j=0; j<totalNodes; j++){
                residual[i][j]=capacity[i][j];
            }
        }
        
        System.out.println("Initial Residual Graph: ");
        printResidual(residual, totalNodes); //print the residual matrix
        
        int maxFlow = 0; //counter for the number of successful matches
        int [] parent = new int [totalNodes];
        
        while(true){ //search for source-to-target paths
            boolean [] visited = new boolean [totalNodes]; //reset per while-loop
            boolean foundPath = DFSRec(source, target, residual, visited, parent, totalNodes);
            
            if(!foundPath){ //DFS find no more paths
                break; //stop the while loop because no more matches can be made
            }
            
            int pathFlow = 1; //bottleneck capacity (smallest edge = 1 --> since every path has edges of capacity 1 - the most the can travel through a path is 1)
            int current = target; //start at the end of the path to walk backwards to the source
            
            while(current!=source){
                int previous = parent[current]; //the previous node to current is its parent in the path
                residual[previous][current] = residual[previous][current]-pathFlow; //since those capacities are now full, setting them to zero
                residual[current][previous] = residual[current][previous]+pathFlow; //add backward edges: to undo step later if needed
                current = previous; //Move one step backward in the path
            }
            maxFlow=maxFlow + pathFlow; //stores the number of applicants that got a job to maximize the number of applicants getting jobs
            
            System.out.println("Residual graph after augmented path " + maxFlow + ":");
            printResidual(residual, totalNodes);
        }
        return maxFlow; //return the total number of matches found
    }
    
    public static boolean DFSRec(int current, int target, int [][] residual, boolean [] visited, int [] parent, int totalNodes){
        visited[current] = true;
        
        if(current == target){
            return true; //path has been found, if current node equals target node
        }
        
        for(int i=0; i<totalNodes; i++){
            if(!visited[i] && residual[current][i]>0){ //if node no visited and path exists
                parent[i]=current;
                
                if(DFSRec(i, target, residual, visited, parent, totalNodes)){
                    return true; //path found from source to target
                }
            }
        }
        return false;
    }
    
    //to see changes in the residual matrix
    public static void printResidual(int [][] residual, int totalNodes){
        
        for(int i = 0; i<totalNodes; i++){
            for(int j = 0; j<totalNodes; j++){
                System.out.print(residual[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}