package com.task_5_1_14.data;

import java.security.SecureRandom;

public class ArraySort {

    public void threeWayStringQuickSortArrays(int[][] arrays)
    {
        threeWayStringQuickSortArrays(arrays, 0, arrays.length - 1, 0);
    }

    private void threeWayStringQuickSortArrays(int[][] arrays, int low, int high, int valueIndex)
    {
        if (low >= high)
        {
            return;
        }

        int lowerThan = low;
        int greaterThan = high;
        
        SecureRandom random = new SecureRandom();
        
        int pivotIndex = random.nextInt(low, high + 1);
        swapArrays(arrays, low, pivotIndex);
        Integer pivot = valueAt(arrays[low], valueIndex);

        int index = low + 1;

        while (index <= greaterThan) 
        {
            Integer currentValue = valueAt(arrays[index], valueIndex);

            if ((currentValue == null && pivot != null) ||
                    (pivot != null && currentValue < pivot))
            {
                swapArrays(arrays, lowerThan++, index++);
            }
            else if ((pivot == null && currentValue != null) ||
                    (pivot != null && currentValue > pivot))
            {
                swapArrays(arrays, index, greaterThan--);
            }
            else
            {
                ++index;
            }
        }

        threeWayStringQuickSortArrays(arrays, low, lowerThan - 1, valueIndex);
        if (pivot != null)
        {
            threeWayStringQuickSortArrays(arrays, lowerThan, greaterThan, valueIndex + 1);
        }
        threeWayStringQuickSortArrays(arrays, greaterThan + 1, high, valueIndex);
    }

    private Integer valueAt(int[] array, int index)
    {
        if (index < array.length)
        {
            return array[index];
        }
        else 
        {
            return null;
        }
    }

    private void swapArrays(int[][] arrays, int index1, int index2)
    {
        int[] temp = arrays[index1];
        arrays[index1] = arrays[index2];
        arrays[index2] = temp;
    }
}