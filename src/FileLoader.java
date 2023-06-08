//student: uwxkz
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class FileLoader
{
    public static Scanner sc = new Scanner(System.in);
    
    //public static ArrayList<String> arr = new ArrayList<String>();
    //public static ArrayList<String> arr2 = new ArrayList<String>();
    public static ArrayList<String> arr3 = new ArrayList<String>();
    public static ArrayList<String> arr4 = new ArrayList<String>();
    public static ArrayList<Integer> arr5 = new ArrayList<Integer>();
    
    public static int[] rowInput = {0}; //should be an integer but defining it as an array so its value can be changed
    public static int[] columnInput = {0};
    
    public static boolean statement = true;
    public static int i = 0;
	
	
	public static String Coordinates(char input1, char input2){//inserts input coordinates into brackets
	    return "(" + input1 + "," + input2 + ")"; 
	}
	
	
	public static void sort(){ //method to sort the inputs from smallest to biggest
	    int temp;
	    
	    int[] array = new int[arr4.size()]; //contains all coordinates
	    
	    for(int i=0; i<arr4.size(); i++){
	        array[i] = Integer.parseInt(arr4.get(i)); //filling in the array
	    }
	    
	    
	    for(int i=0; i<array.length; i++){ //sorting it
	        for(int j=i; j<array.length; j++){
	            if(array[i] > array[j]){
	                temp = array[i];
	                array[i] = array[j];
	                array[j] = temp;
	            }
	        }
	    }
	    
	    int count = 0;
	    
	    for(int i=0; i<array.length; i++){ //counting the duplicates if there are any
	        for(int j = i + 1; j<array.length - 1; j++){
	            if(array[i]==array[j]){
	                count++;
	                j = j + 1;
	            }
	        }
	    }
	    
	    //System.out.println(count);
	    int[] array2 = new int[array.length - count]; //creating a new array that should be free of duplicates
	    
	    for(int i=0; i<array2.length; i++){ //eliminating the duplicates
	        for(int j=0; j<array.length; j++){
	            if(Arrays.toString(array2).contains(Integer.toString(array[j]))){
	                j = j + 1;
	            }
	            else{
	                array2[i] = array[j];
	            }
	        }
	    }
	    
	     for(int i=0; i<array2.length; i++){ //sorting the new array (which contains no duplicates)
	        for(int j=i; j<array2.length; j++){
	            if(array2[i] > array2[j]){
	                temp = array2[i];
	                array2[i] = array2[j];
	                array2[j] = temp;
	            }
	        }
	    }
	    
	    for(int i=0; i<array2.length; i++){
	        arr5.add(array2[i]); //arr5 contains all the inputs (with no duplicates)
	    }
	    
	    /*for(int i=0; i<array.length; i++){
	        System.out.print(array[i] + ", ");
	    }
	    
	    System.out.println("");
	    
	    for(int i=0; i<array2.length; i++){
	        System.out.print(array2[i] + ", ");
	    }*/
	    
	}
	
	
	
	public static void readFile(){
	    while(statement){
	        String Input = sc.nextLine();
	        
	        for(int i=0; i<Input.length(); i++){
	            if(Input.charAt(i)== ' '){//since the input will come in the format of number {space} number
	                arr3.add(Coordinates(Input.charAt(i - 1), Input.charAt(i + 1))); //adding both onto one arraylist
	                arr4.add(Character.toString(Input.charAt(i - 1)));
	                arr4.add(Character.toString(Input.charAt(i + 1)));

	                
	            }
	                
	            else if(Input.equals("close scanner")){
	                statement = false;
	                return;
	            }
	        }
	    }
	}
	
	public static void insert(int row, int column, int[][] matrix){
	    int firstelement = matrix[0][0];
        if(row == 0 || column == 0){
            
        }
        else{
        matrix[(row - firstelement)][(column - firstelement)] = 1; //inserting a 1 when there is a path between row and col
        }    
	}
	
	
	public static void constructAdjacencyMatrix(String[] array, int startingcity, int targetcity){
	    int[][] matrix = new int[arr5.size() + 1][arr5.size() + 1]; //adding one row and colum to represent the indices of
	    matrix[0][0] = 0;                                           //the inputs
	    
	    for(int j=1; j<matrix.length; j++){
		    int i=0;
		    matrix[i][j] = arr5.get(j - 1); //filling the rows with the inputs of arraylist 5
		}

	    for(int i=1; i<matrix.length; i++){
	        int j=0;
	        matrix[i][j] = arr5.get(i - 1); //filling the columns
	    }
	    
	    
	    
	    for(int i=0; i<array.length; i++){
            for(int j=0; j<array[i].length(); j++){
                if(array[i].charAt(j) == '('){
                    rowInput[0] = Integer.parseInt(String.valueOf(array[i].charAt(j + 1)));
                }
                if(array[i].charAt(j) == ')'){
                   columnInput[0] = Integer.parseInt(String.valueOf(array[i].charAt(j - 1)));
                }//defining row and column inputs to be used as arguments for insert to determine where 1 should be
            }
            
            insert(rowInput[0], columnInput[0], matrix);
        }
        
        
		for(int i=0; i<matrix.length; i++){
		    for(int j=0; j<matrix[i].length; j++){
		        System.out.print(matrix[i][j]);
		    }
		    System.out.println("");
		}
		
		System.out.println("");
		
		int[][] matrixmultiplied = new int[matrix.length][matrix.length]; //creating a new matrix for the length of paths 
		
		for(int i=1; i<matrix.length; i++){ //matrix multiplication
		    for(int j=1; j<matrix[i].length; j++){
		        matrixmultiplied[i][j] = 0;
		        for(int k=1; k<matrix.length; k++){
		            matrixmultiplied[i][j] += matrix[i][k] * matrix[k][j];
		        }
		        System.out.print(matrixmultiplied[i][j]);
		    }
		    System.out.println("");
		}
		
		System.out.println("");
		System.out.println("The length of paths between the number " + startingcity + " and the number " //output
		+ targetcity + " is: " + matrixmultiplied[startingcity][targetcity]);
		
	}


	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
	readFile();
	sort();
	System.out.println("");
	
	String[] array3 = new String[arr3.size()]; //converting an arraylist into array3 which displays the coordinates
	
	for(int i=0; i<arr3.size(); i++){
	    array3[i] = arr3.get(i);
	}
	
	constructAdjacencyMatrix(array3, 3, 4);

	
	}
	
	
	
}
