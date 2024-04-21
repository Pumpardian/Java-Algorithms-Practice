package com.task_3_2_13;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.task_3_2_13.data.NonrecursiveBST;
import com.task_x_x_xx.input.Input;

public class Main 
{
    public static void main(String[] args) 
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
    	List<String> input = new ArrayList<>();
    	Input.scan(logger, input);
        String[] arr = input.toArray(new String[0]);
        int size = arr.length;
        NonrecursiveBST<String, Integer> tree = new NonrecursiveBST<>();
        for (int i = 0; i < size; ++i)
        {
            tree.put(arr[i], i);
        }
        for (String str : tree.keys())
        {
            logger.info(() -> str + " " + tree.get(str));
        }
    }
}
