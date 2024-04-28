package com.task_5_1_14;

import java.util.StringJoiner;
import java.util.logging.Logger;

import com.task_5_1_14.data.ArraySort;

public class Main
{
    public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        ArraySort arraySort = new ArraySort();

        int[] array1 = {20, 10};
        int[] array2 = {20, 10, 1};
        int[] array3 = {20, 10, 0};
        int[] array4 = {20, 10, -10};
        int[] array5 = {20, 10, -10, 30};
        int[] array6 = {20, 10, -10, 1};
        int[] array7 = {10, 999, 1, 9, 5};
        int[] array8 = {10, 999, 2, 10, 5};
        int[] array9 = {1, 70, 2, 10, 5, 90};
        int[] array10 = {15};

        int[][] arrays = {array1, array2, array3, array4, array5, array6, array7, array8, array9, array10};

        arraySort.threeWayStringQuickSortArrays(arrays);

        StringJoiner sortedArrays = new StringJoiner("\n");

        for (int[] array : arrays)
        {
            StringJoiner sortedArray = new StringJoiner(", ");

            for (int value : array)
            {
                sortedArray.add(String.valueOf(value));
            }
            sortedArrays.add(sortedArray.toString());
        }

        logger.info("Sorted array");
        logger.info(() -> "" + sortedArrays);
    }
}
