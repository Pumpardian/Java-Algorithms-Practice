package com.task_2_5_14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import com.task_2_5_14.data.Domain;
import com.task_x_x_xx.input.Input;

public class Main
{
	public static void main(String[] args)
	{
		Logger logger = Logger.getLogger(Main.class.getName());
	    List<String> list = new ArrayList<>();
	    Input.scan(logger, list);
	    String[] names = list.toArray(new String[0]);
	    int size = names.length;
	    Domain[] domains = new Domain[size];
	    for (int i = 0; i < size; ++i)
	    {
	        domains[i] = new Domain(names[i]);
	    }
	    Arrays.sort(domains);
	    for (int i = 0; i < size; ++i)
	    {
	    	int x = i;
	        logger.info(() -> "" + domains[x]);
	    }
	}
}
