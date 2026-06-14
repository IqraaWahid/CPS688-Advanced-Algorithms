/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */

import java.util.Scanner;

public class Problem4{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
		
	int n = input.nextInt(); //number of candies
	int [] value = new int [n]; //stores the values of the n candies
	int [] weight = new int [n]; //stores the weights of the n candies
		
	//store the values of the candies:
	for(int i = 0; i < n; i++){
            value[i] = input.nextInt();
        }
		
        //store the weights of the candies:
	for(int i = 0; i < n; i++){
            weight[i] = input.nextInt();
        }
		
	int w = input.nextInt(); //max weight bag can carry
		
	int [][] dp = new int [n+1][w+1]; //dynamic programming 2-D array - dimensions are including 0 rows and columns
		
	//start at row=1 and col=1 because row=0 and col=0 is filled with default zeros 
	for(int row = 1; row <= n; row++){ //row must be smaller than or equal to the number of candies
            for(int col = 1; col <= w; col++){//col must be smaller than or equal to the weight of the knapsack
		if(weight[row-1] <= col){ //weight of candies must be smaller than weight bag holding
		    dp[row][col] = Math.max(dp[row-1][col-weight[row-1]] + value[row-1], dp[row-1][col]); //formula obtained from lecture slides
		}
		else{
		    dp[row][col] = dp[row-1][col];//otherwise, incase weight of candy is greater than the weight of the bag atm
		}
            }
        }
		
        //print the matrix (to check steps):
        System.out.println("Matrix: ");
        for(int row = 0; row <= n; row++){
            for(int col = 0; col <= w; col++){
		System.out.print(dp[row][col] + "\t");
            }
            System.out.println();
            System.out.println();
        }
		
	System.out.println(dp[n][w]); //greatest value is at the most bottom-right of the matrix 
    }
}
