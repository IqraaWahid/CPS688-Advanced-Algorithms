/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author i2wahid
 */

import java.util.Scanner;

public class Problem1{
    //instance variables:
    //chose to use static variables and methods so don't have to work with objects; this would make code simpler :)
    static int N; //stores the size of the board
    static int [][] board; //stores board in matrix form (the queen positions (1) and empty positions (0))
	
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
		
	System.out.println("Enter the number of the matrix size: ");
	N = input.nextInt();
	board = new int [N][N]; //create N x N board(array)
		
	//start by placing the queens from the first column of the array - zero
	if(solveNQueens(0)){
            System.out.println("Solution Matrix: ");
            printBoard();
	}
	else{
            System.out.println("No solution.");
	}
    }
	
    //recursive function to place the queens column by column
    public static boolean solveNQueens(int col){
        //board solution ready (all queens have been placed in their correct places)
        if(col >= N){ //done
            return true;
        }

        //try each row in the current column
        for(int row = 0; row < N; row++){
            //check if the current position is safe
            if(isSafe(row, col)){ // is it is safe then...
                //place queen at that position
                board[row][col] = 1;

                //recursive call: to place queen in the next column depending on the current one
                if(solveNQueens(col + 1)){
                    return true; //done
                }

                //if it goes through all the rows in the current column and finds no position, backtrack:
                board[row][col] = 0; //basically you go back to the previous column from the column you were gonna work on since that didn't have any good position 
            }
        }
        return false; //all rows in the current column checked and no proper position found then return false for the recursive call line 39 for the current column
    }
	
    //check if the queens can be placed safley:
    public static boolean isSafe(int row, int col){   
        //checks for queens in the same row
        for(int c = 0; c < col; c++){
            if(board[row][c]==1){ //same row, column changes
                return false;
            }
        }

        //checks for queens in the same diagonal (lower-left)
        for(int c = col; c >= 0 ; c--){
            for(int r = row; r >= 0 ; r--){
                //if same amount subtracted from row and col then we looking at low-left diagonal
                // and check if that tile also has a queen
                if ((row - r) == (col - c) && board[r][c]==1){
                    return false;
                }
            }
        }

        //checks for queens in the same diagonal (upper-right)
        for(int c = col; c >=0; c--){
            for(int r = row; r < N; r++){
                if((col-c) == (r - row) && board[r][c]==1){
                    return false;
                }
            }
        }

        //if safe:
        return true;
    }
	
    public static void printBoard(){
	for(int row = 0; row < N; row++){
	    for(int col = 0; col < N; col++){
	        System.out.print(board[row][col] + " ");
	    }
	System.out.println();
        }
    }
}
