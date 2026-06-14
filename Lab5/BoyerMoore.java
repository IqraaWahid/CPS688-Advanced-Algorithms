import java.util.Scanner;
import java.util.ArrayList;

public class BoyerMoore{
    private static final int R=256; // Radix
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the text: ");
        String text = input.nextLine();
        System.out.print("Enter the pattern: ");
        String pattern=input.nextLine();
        
        int N = text.length(); //length of the text
        int M = pattern.length(); //length of the pattern
        boyerMoore(text, pattern, N, M);
    }

    public static void boyerMoore(String text, String pattern, int N, int M){
        int [] right = new int[R]; //precompute index of rightmost occurence of the character from the text in the pattern
        
        //initially set all the elements of the right array to -1
        for(int c=0;c<R; c++){
            right[c]=-1;
        }
        
        //fill the right array with the index of the rightmost occurence of the character from the text in the pattern
        for(int j=0; j<M; j++){
            right[pattern.charAt(j)]=j;
        }
        
        /*
        //testing the right array:
        System.out.println("Right array: ");
        for(int i=0; i<R; i++){
            System.out.print(right[i]+" ");
        }
        */
        
        search(text, pattern, N, M, right);
    }
    
    public static void search(String text, String pattern, int N, int M, int [] right){
        int skip;
        //System.out.print("Pattern " + pattern + " found at index "); // they want difference between index and indices
        ArrayList <Integer> answer = new ArrayList<>(); //stores the answer(s)
        
        for(int i=0; i<=N-M; i=i+skip){ //starting from the left-most element of the text, and i must be smaller than or equal to N-M; otherwise, pattern not found since N (length of text) is too short
            skip=0;
            for(int j=M-1; j>=0; j--){ //starting from the right-most element of the pattern
                if(pattern.charAt(j)!=text.charAt(i+j)){ //if there is a mismatch
                    skip = Math.max(1, j-right[text.charAt(i+j)]); //choose the greatest: if it makes the pattern go backwards then incerement i by 1 only
                    break; //(update i, the outter-for-loop) since we reached a point where mismatch occured and have calculated the skip value, now implement that skip onto the text
                }
                
            }
            if(skip==0){ //if the inner-for-loop has been fully executed, meaning the pattern has been found)
                answer.add(i);
                skip = 1; //update skip, so i can be updated and doesn't get stuck in an infinite loop
            }
        }
        
        //if there is one answer:
        if(answer.size()==1){
            System.out.print("Pattern " + pattern + " found at index ");
            for(int i=0; i<answer.size(); i++){
                System.out.print(answer.get(i) + " ");
            }
        }
        
        //if there are multiple answers
        else{
            System.out.print("Pattern " + pattern + " found at indices ");
            for(int i=0; i<answer.size(); i++){
                System.out.print(answer.get(i));
                
                //so no extra commas are added and exact output expected is matched
                if(i<answer.size()-1){
                    System.out.print(", ");
                }
            }
        }
    }
}