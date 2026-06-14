/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */

import java.util.Scanner;

public class Problem5{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
		
	int [] A = new int [1000]; //to store the input number sequence - I chose to use an array, rather than an arrayList, to stay consistent with the types of data types being used (dp will also be an array)
	int n = 0; //stores the number of numbers in the sequence
		
	//while loop will continue until non-integer inputted
	while(input.hasNextInt()){
            A[n] = input.nextInt();
            n++;
        }
		
	int [] B = new int [n]; //this array will store the sorted version of array A (only unique integers - no duplicates)
	int m = 0; //stores the number of numbers added into B
		
	//Filling in B such that it has only unique values of A
	for(int i = 0; i < n; i++){
            boolean found = false; //initally say number hasn't been seen before
		    
            //if element of A is already found in B before
            for(int j = 0; j < m ; j++){
		if(A[i] == B[j]){
		    found = true; //since element is already in B, do nothing
		}
            }
		    
            if(!found){ //if element in A not found in B, then add it into B:
		B[m] = A[i]; //add element from A into B
		m++; //increase the size of array B
            }
        }
		
	//Now sort B in ascending order:
	for(int i = 0; i < m-1; i++){ //first number - smaller than m-1 since we add m++ additional in the above if-statement
            for(int j = i + 1; j < m; j++){ //second number
		if(B[i] > B[j]){ //if first number greater than seond number
		    int temp = B[i]; //store the first number in 'temp'
		    B[i] = B[j]; //store in the first number's place the second number
		    B[j] = temp; //and in the second number's place the first number which is stored in 'temp'
		}
            }
        }
		
        //dynamic programming:
        int [][] dp = new int [n+1][m+1]; //row has A and column has B - dynamic programming matrix
	//row and columns are 'n+1' and 'm+1' since we have to add an extra row and column of zeros 
		
	//Filling in dp following LCS
	for(int i = 1; i <= n; i++){//start i = 1 and j = 1 since i = 0 and j = 0 are zeros
            for(int j = 1; j <= m; j++){ //row stays same and column changes each time, comparing fixed A with changing B
		//if current value of A matches that of B
		if(A[i-1] == B[j-1]){//i-1 and j-1 because we wanty to start from index zero for the both arrays
		    dp[i][j] = dp[i-1][j-1]+1; //diagonal + 1 since matching extends the subsequence
		}
		else if(dp[i-1][j] > dp[i][j-1]){ //no match: top is greater than left
		    dp[i][j] = dp[i-1][j]; //copy top value since it gives a longer subsequence
		}
		else{ //no match: left value is greater than or equal to the top value
		    dp[i][j] = dp[i][j-1]; //j-1 means the column goes one left, which means the left value at the same row since row is still i
		}
            }
        }
		
	//print matrix:
	System.out.println("Matrix: ");
        for(int row = 0; row <= n; row++){
            for(int col = 0; col <= m; col++){
		System.out.print(dp[row][col] + " ");
            }
            System.out.println();
        }
		
        //backtracking:
	int [] list = new int [dp[n][m]]; //dp[n][m] is the bottom-right value stored in the matrix which is the length of the longest common subsequence
	int index = dp[n][m] - 1; //start inserting from the end since backtracking starts from the end (bottom-right)
		
	//to start from bottom right block of the matrix
	int i = n; //row position
	int j = m; //column position
		
	//find LIS: backtracking
	while(i > 0 && j > 0){//thats where the matrix comparing ends
            //match:
            if(A[i-1] == B[j-1]){
		list[index] = A[i-1];
		index--; //reduce to work with the one before now
		//go diagonal up-left
		i--;
		j--;
            }
		    
            else if(dp[i-1][j]>=dp[i][j-1]){ //if top>=left value
		//move up:
		i--;
            }
		    
            else{
		j--; //left value > top value, move one left
            }
	}
		
        System.out.println("LIS = " + dp[n][m]); //length of the LIS is dp[n][m] - last value (bottom-right)
        System.out.print("LIS is: ");
		
        for(int k = 0; k < list.length; k++){
            System.out.print(list[k] + " ");
        }
    }
}