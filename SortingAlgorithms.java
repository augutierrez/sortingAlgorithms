package sortingAlgorithms;
import java.io.*;
import java.util.Arrays;
import java.util.Random;


public class SortingAlgorithms implements SortingInterface
{
    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed)
    {
        //low index and high index determines what range the user wants us to sort from
        Comparable curr;
        int j;

        for (int i = lowindex + 1; i <= highindex; i++) {
            curr = array[i];
            j = i - 1;
            if (reversed) {
                while (j >= lowindex && array[j].compareTo(curr) == -1) {
                    array[j + 1] = array[j]; // shift elems to the right
                    j--;
                }
            }
            else {
                while (j >= lowindex && array[j].compareTo(curr) == 1) {
                    array[j + 1] = array[j]; // shift elems to the right
                    j--;
                }
            }
            array[j + 1] = curr;

        }
    }


    public void shakerSort(Comparable[] array, int lowindex, int highindex, boolean reversed)
    {
        Comparable temp;
        int comp;
        if(reversed ) //checking if reversed, if it is, I basically reverse the greater than and less than checks
            comp = 1;

        else
            comp = -1;

        int start = lowindex;
        int end = highindex;
        while(start < end)
        {
            for(int i = start; i < end; i++)
            { // < because I am comparing with arr[i+1]


                if (array[i + 1].compareTo(array[i]) == comp) {
                    temp = array[i];
                    array[i] = array[i + 1]; //swaps their values
                    array[i + 1] = temp;
                }
            }
            end --;
            for(int i = end; i > start; i--)
            { // < because I am comparing with arr[i+1]
                if(array[i-1].compareTo(array[i]) == (comp * -1))
                {
                    temp = array[i];
                    array[i] = array[i-1]; //swaps their values
                    array[i-1]= temp;
                }
            }
            start++;
        }

    }
    public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex, boolean reversed)
    {
        int indexOfPivot;
        if (lowindex <= highindex)
        {
            indexOfPivot = partition(array, lowindex, highindex,reversed); // the index of pivot element
            randomizedQuickSort(array, lowindex, indexOfPivot - 1,reversed);
            randomizedQuickSort(array, indexOfPivot + 1, highindex,reversed);
        }
    }

    public int partition(Comparable[] array, int lowindex, int highindex, boolean reversed)
    {
        int random = ((int)Math.random() * ((highindex - lowindex) + 1)) + lowindex;
        Comparable temp = array[random];
        array[random] = array[highindex];
        array[highindex] = temp;
        int i = lowindex;
        int j = highindex - 1;
        int num;
        if(reversed)
        { //checking if reversed, if it is, I basically make i check for greater number, and j for less than
            num = -1;
        }
        else
            num = 1;
        while(i<=j){
            while(array[i].compareTo(array[highindex]) == (num * -1) && i < highindex){// add the second part to protect it from going out of bounds
                i++;
            }
            while(j >= lowindex && (array[j].compareTo(array[highindex]) == num || array[j].compareTo(array[highindex]) == 0))
            {// add the second part to protect it from going out of bounds
                j--;
            }
            if(i < j)
            {
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                i++;
                j--;
            }
        }
        temp = array[highindex];
        array[highindex] = array[i];
        array[i] = temp;
        return i;
    }

    public void hybridSort(Comparable[] array, int lowindex, int highindex, boolean reversed){
        int indexOfPivot;
        if (lowindex <= highindex) {
            indexOfPivot = partition(array, lowindex, highindex,reversed); // the index of pivot element
            if (indexOfPivot - 1 - lowindex > (array.length * .1)) {
                hybridSort(array, lowindex, indexOfPivot - 1, reversed);
            }
            else{
                insertionSort(array, lowindex, indexOfPivot -1 , reversed);
            }
            if (highindex - indexOfPivot + 1 > (array.length * .1)) {
                hybridSort(array, indexOfPivot + 1, highindex, reversed);
            }
            else{
                insertionSort(array, indexOfPivot + 1, highindex, reversed);

            }

        }

    }

    //THIS IS THE SLOWER HYBRID SORT
//    public void hybridSort3(Comparable[] array, int lowindex, int highindex, boolean reversed){
//        hybridSort2(array, lowindex,highindex, reversed);
//        insertionSort(array, lowindex, highindex,reversed);
//    }
//
//    public void hybridSort2(Comparable[] array, int lowindex, int highindex, boolean reversed){
//        int indexOfPivot;
//        int counter;
//        if (lowindex <= highindex) {
//            indexOfPivot = partition(array, lowindex, highindex, reversed); // the index of pivot element
//            if (indexOfPivot - 1 - lowindex > (array.length * .1)) {
//                hybridSort2(array, lowindex, indexOfPivot - 1, reversed);
//            }
//            if (highindex - indexOfPivot + 1 > (array.length * .1)) {
//                hybridSort2(array, indexOfPivot + 1, highindex, reversed);
//            }
//        }
//    }

    public void externalSort(String inputFile, String outputFile, int n, int k){
        //number of files i'll create
        int numChunks = n/k;
        if((n%k) != 0)
            numChunks++;

        //create a bufferedreader list
        BufferedReader[] buffList = new BufferedReader[numChunks];
        String line;
        Comparable[] array = new Comparable[k];

        //reading input
        try (BufferedReader input = new BufferedReader(new FileReader(inputFile))) {
            for (int i = 0; i < numChunks; i++) {//writes and sorts the temp files
                for (int j = 0; j < k; j++) { // this copies numbers into the files
                    line = input.readLine();
                    array[j] = Integer.parseInt(line);
                }
                randomizedQuickSort(array, 0, array.length -1, false); // this sorts the files
                BufferedWriter file = new BufferedWriter(new FileWriter("temp" + i));
                for(int a = 0; a < k; a++){
                    file.write(array[a].toString());
                    file.flush();
                    file.newLine();
                }
                file.close(); // closes input file
                BufferedReader insert = new BufferedReader(new FileReader("temp" + i));
                buffList[i]= insert; //inserting the opened files in the list
            }
            Comparable[] inputArray = new Comparable[numChunks];


            // write a loop that opens buffered readers for each file
            //after that I can work on this loop
            try(PrintWriter writer = new PrintWriter(outputFile)){
                for(int a = 0; a < numChunks; a++){
                    inputArray[a] = Integer.parseInt(buffList[a].readLine());
                }
                for(int b = 0; b < n ; b++){
                    // makes it the biggest integer possible, every number will be less than it
                    Comparable min = Integer.MAX_VALUE;
                    int minIndex = 0;
                    int counter = 0;
                    while(counter < numChunks) {
                        if(inputArray[counter] != null && min.compareTo(inputArray[counter]) > 0) {
                            minIndex = counter;
                            min = inputArray[counter];
                        }
                        counter++;
                    }

                    writer.println(inputArray[minIndex]);//writes in file
                    String number = buffList[minIndex].readLine();
                    if(number == null)
                        inputArray[minIndex] = null;
                    else
                        inputArray[minIndex] = Integer.parseInt(number);
                }
            }
            catch (FileNotFoundException except){
                System.out.println("Could not open writing file");
                except.printStackTrace();
            }

            //closes all the arrays once we are done with them
            for(int a = 0; a < numChunks; a++){
                buffList[a].close();
            }
            System.out.println("FILE ARRAY" + Arrays.toString(inputArray));
        }

        catch (Exception FileNotFoundException){
            System.out.println("Could not find file.");
        }
    }
}




