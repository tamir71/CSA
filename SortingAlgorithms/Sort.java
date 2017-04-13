import java.io.*;
import java.util.Scanner;

/**
 *You can choose three types of sorts on various sizes of unsorted lists.
 *
 *@author Tamir Enkhjargal
 *
 *@version 1.2
 *Current version implements table.
 */
public class Sort {
    public static int fileChoice;
    public static long startTime;
    public static long endTime;
    public static long duration;
    
    /**
     * Running main() allows the user to choose a file and algorithm
     * This will return with the first ten, last ten, and time took to sort
     * 
     */
    public static void main(String[] args) {
        Scanner fileInputScan = new Scanner(System.in);
        System.out.println("Enter Input File Number [1-4]: "); //Take User File Input Choice
        fileChoice = fileInputScan.nextInt();
        
        try {
            Scanner algorithmInputScan = new Scanner(new File("input" + fileChoice + ".txt"));
            System.out.println("Enter Sorting Algorithm Choice [Selection, Insertion, Merge]: ");
            chooseAlg(stringToInt(algorithmInputScan.nextLine().split(","))); //Calls Interpretation
            fileInputScan.close();
            algorithmInputScan.close();
        }
        
        catch (FileNotFoundException error) {
            error.printStackTrace();
            System.exit(0);
        }
        
    }

    /**
     *Takes stringArray and converts to intArray
     *
     *Parameter: Raw array of numbers as strings
     *Return: intArray that is to be sorted
     */
    public static int[] stringToInt(String[] stringArray) {
        int intArray[] = new int[stringArray.length]; //Make intArray as long as stringArray
        
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        
        return intArray; //Returns our unsorted array of integers
    }

    /**
     *Reads user algorithm choice
     *Then runs interpreted choice
     *
     *Parameter: Unsorted array of integers
     *Return: none
     */
    public static void chooseAlg(int[] intArray) { 
        Scanner select = new Scanner(System.in); //Algorithm Selection
        String selectedAlg = select.nextLine(); //Takes Algorithm Choice
                
        if (selectedAlg.equals("Selection")) {
            runStats(selectionSort(intArray)); //Run Selection Sort
            System.out.printf("\n");
        }
        else if (selectedAlg.equals("Insertion")) { 
            runStats(insertionSort(intArray)); //Run Insertion Sort
            System.out.printf("\n");
        }
        else if (selectedAlg.equals("Merge")) { 
            runStats(mergeSort(intArray)); //Run Merge Sort
            System.out.printf("\n");
        }
        else {
            System.out.println("Algorithm not found");
        }
        
        select.close();
    }
    
    /**
     *Runs Selection sorting method
       *Replace first element with smallest value
       *Loop through array
     *Returns sorted array
     *
     *Parameter: Unsorted array of integers
     *Return: Sorted array of integers
     */
    public static int[] selectionSort(int[] intArray) {
        int j;
        int temp;
        int min;
        startTime = System.currentTimeMillis(); //Set time before algorithm execution
        
        for (int i = 0; i < intArray.length - 1; i++) { //Run through entire array
            min = i; //Set first value as 'min'
            
            for (j = i + 1; j < intArray.length; j++) { //Check value next to i
                if(intArray[min] > intArray[j]) {
                    min = j; //Set new minimum
                }
            }
            
            temp = intArray[i]; 
            intArray[i] = intArray[min]; 
            intArray[min] = temp; //Swaps elements at min and i
        }
        
        endTime = System.currentTimeMillis(); //End time after algorithm
        duration = endTime - startTime;
        System.out.println(/* "The sort took " + */ duration /* + " milliseconds" */);
        return intArray;
    }
    
    /**
     *Runs Insertion sorting method
       *Continuously sort numbers from beginning
     *
     *Parameter: Unsorted array of integers
     *Return: Sorted array of integers
     */
    public static int[] insertionSort(int[] intArray){
        int pos;
        int temp;
        startTime = System.currentTimeMillis(); //Set time before algorithm execution
        
        for (int i = 1; i < intArray.length; i++ ) {
            pos = i;
            while (pos != 0 && intArray[pos-1] > intArray[pos]) {
                temp = intArray[pos-1];  //
                intArray [pos-1] = intArray[pos]; //Element swap
                intArray [pos] = temp;       //
                pos--;
            }
        }
        
        endTime = System.currentTimeMillis(); //End time after algorithm
        duration = endTime - startTime;
        System.out.println(/* "The sort took " + */ duration /* + " milliseconds" */);
        return intArray;
    }

    /**
     *Runs Merge sorting method
       *Requires three merge methods
       *Recursively merge sorts
     *
     *Parameter: Unsorted array of integers
     *Return: Sorted array of integers
     */
    public static int[] mergeSort(int[] intArray) {
        startTime = System.currentTimeMillis(); //Set time before algorithm execution
        
        int[] temp = new int[intArray.length];
        mergeSort(intArray, temp, 0, intArray.length-1); //Start of merge sort
        
        endTime = System.currentTimeMillis(); //End time after algorithm
        duration = endTime - startTime;
        System.out.println(/* "The sort took " + */ duration /* + " milliseconds" */);
        return intArray;
    }
    
    /**
     *Creates two arrays, halved for algorithm
       *Requires another merge method
       *Recursively merge sorts
       *Second part to merge sort
     *
     *Parameter: Given intArray, a temporary array, and the two cut halves
     *Return: none
     */
    public static void mergeSort(int[] intArray, int[] temp, int left, int right) {
        if (left < right) {                                //Checking if already sorted or not
            
            int mid = (left + right)/2;                    //Get midpoint if odd array length
            
            mergeSort(intArray, temp, left, mid);          //Sort first half (first value to midpoint)
            
            mergeSort(intArray, temp, mid+1, right);       //Sort second half (midpoint to last value)
            
            merge(intArray, temp, left, mid+1, right);     //Finally merge sorted halves
        }
    }
    
    /**
     *Used to sort each recursively sized halves
       *Checks values of first half, second half, and merges accordingly
       *Third part to merge sort 
     *
     *Parameter: Given intArray, a temporary array, and int values of each half header
     *Return: none
     */
    public static void merge(int[] intArray, int[] temp, int left, int mid, int right) {
        //Copy intArray into temp array
        for (int i = left; i <= right; i++) {
            temp[i] = intArray[i];   
        }
        
        int i = left;   //Temporary counter for left
        int j = mid+1;  //Temporary counter for right
        int k = left;      //Temporary index counter
        
        //While halves are not sorted yet
        while (i <= mid && j <= right) { 
            
            //If left is smaller than right
            if (Integer.compare(intArray[left], intArray[mid]) <= 0) { 
                //Sort left value to temp
                intArray[k] = temp[i];
                i++;
            }
            //If right is smaller than left
            else {
                //Sort right value to temp
                intArray[k] = temp[j];
                j++;
            }
            k++;
            
        }
        
        //When a half is already sorted, just fill rest of values into temp
        while (i <= j-1) {
            intArray[k] = temp[i];
            i++;
        }
        
    }

    /**
     *Prints out first ten and last ten elements of array
     *Prints out time taken to run sort.
     *
     *Parameter: Sorted array of integers
     *Return: none
     */
    
    public static void runStats(int[] sorted) { 
        System.out.print("First 10 values: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.printf("\n");
        System.out.print("Last 10 values: ");
        for (int i = sorted.length - 10; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.printf("\n");
    }
   
    public static void testAllTable(String[] args) {
        
        System.out.println("Selection   ");
        System.out.println("Insertion   ");
        System.out.println("Merge       ");
        
        System.out.println("Input 1     ");
        Scanner file1 = new Scanner(new File("input/input1.txt"));
        int[] file1 = stringToInt(file1.nextLine().split(","));
        selectionSort(file1);
        insertionSort(file1);
        mergeSort(file1);
        
        System.out.println("Input 2     ");
        Scanner file2 = new Scanner(new File("input/input2.txt"));
        int[] file2 = stringToInt(file2.nextLine().split(","));
        selectionSort(file2);
        insertionSort(file2);
        mergeSort(file2);
        
        System.out.println("Input 3     ");
        Scanner file3 = new Scanner(new File("input/input3.txt"));
        int[] file3 = stringToInt(file3.nextLine().split(","));
        selectionSort(file3);
        insertionSort(file3);
        mergeSort(file3);
        
    }
    
    public static void timeDuration(String[] args) {
           
    }
    
}
