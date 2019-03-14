package sortingAlgorithms;
import java.util.Arrays;
import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        Comparable[] array = {1, 5, 7, 10, 2, 6, 9, 100, 0, 20, 22, 39, 3, 8, 19, 15, 14, 11, 4, -1, 48, 56};
        SortingAlgorithms sort = new SortingAlgorithms();

        sort.randomizedQuickSort(array, 0, array.length -1 , false);
        sort.insertionSort(array,0,array.length-1,true);
        sort.shakerSort(array,0,array.length -1, false);
        sort.randomizedQuickSort(array, 0,array.length-1, true);
        sort.hybridSort(array, 0, array.length -1 , false);
//        sort.hybridSort3(array, 0, array.length - 1, true);
        sort.insertionSort(array, 0, array.length-1, false);

//EFFICIENCY TESTING
//        Comparable[] list = new Comparable[100000];
//        Random randomGenerator = new Random();
//        long startTime = System.currentTimeMillis();
//        for(int i = 0; i < 1; i++) {
//            for (int j = 0; j < 100000; j++){
//                Comparable number = randomGenerator.nextInt();
//                list[j] = number;
//            }
//            sort.hybridSort(list, 0, list.length -1, true);
//        }
//        long endTime = System.currentTimeMillis();
//        double runningTime = ((double) (endTime - startTime)) / 1;
//        System.out.println(runningTime);

   }
}
