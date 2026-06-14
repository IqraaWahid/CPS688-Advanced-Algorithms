/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */

import java.util.Scanner;

public class Problem1 {
    public static void main (String [] args){
        Scanner input = new Scanner(System.in);
		
	int n = input.nextInt(); //length of the rod
		
        int i = 1; //tracks the index for price array (length of the rod)
	int [] p = new int [n+1]; //price array (stores price at each rod length)
		
		
	while(i <= n){
            p [i] = input.nextInt();
            i++;
        }
		
	//we will be using dynamic programming for this discrete knapsack problem (with repetitions)
	//Bottom-Up-Cut-Rod (p, n)
	int [] dp = new int [n+1]; //stores the max. revenue at each length
	dp[0] = 0; //at length 0, the revenue is also zero
		
	//fill dp []
	for(int j = 1; j<=n ; j++){ //size of rod or index of dp
            int q = Integer.MIN_VALUE; //negative infinite (smallest value)
            for(i = 1; i<=j; i++){ //piece (length) being cut off, must be smaller than or equal to j (size of rod)
                //p[i] + dp[j-i] --> p[i] = value of the first piece cut off
		//dp[j-i] = maximum value obtainable from the remaining rod
		q = Math.max(q, p[i] + dp[j-i]);
            }
            dp[j]=q; //maximum revenue at length j is q
        }
		
	//print the dp array:
	System.out.println("\nDP Array: ");
	for(int k = 0; k<=n; k++){
            System.out.print(dp[k] + " ");
        }
	System.out.println("\n");
		
	System.out.println(dp[n]); //maximum value obtainable by cutting up the rod and selling the pieces (is the last element of the dp [])
    }
}

