package com.tasks_3_2_25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.task_3_2_6.data.BST;
import com.task_x_x_xx.input.Input;

public class PerfectBalance 
{
	private static Logger logger = Logger.getLogger(PerfectBalance.class.getName());
	
    private static void perfect(BST<String, Integer> bst, String[] arr)
    {
        Arrays.sort(arr);
        perfect(bst, arr, 0, arr.length - 1);
        logger.info("");
    }
    
    private static void perfect(BST<String, Integer> bst, String[] arr, int low, int high)
    {
        if (high < low)
    	{
    		return;
    	}
        int mid = low + (high - low) / 2;
        bst.put(arr[mid], mid);
        logger.info(() -> arr[mid] + " ");
        perfect(bst, arr, low, mid-1);
        perfect(bst, arr, mid+1, high);
    }

    public static void main(String[] args)
    {
    	List<String> input = new ArrayList<>();
    	Input.scan(logger, input);
        String[] words = input.toArray(new String[0]);
        BST<String, Integer> tree = new BST<>();
        perfect(tree, words);
    }
}
