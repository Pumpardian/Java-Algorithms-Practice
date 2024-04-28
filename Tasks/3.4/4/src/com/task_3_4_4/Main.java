package com.task_3_4_4;

import java.util.logging.Logger;
import com.task_3_4_4.data.PerfectHashFunction;

public class Main 
{
	 public static void main(String[] args)
	 {
		Logger logger = Logger.getLogger(Main.class.getName());
        int[] values = PerfectHashFunction.perfectHashFunction();

        if (values != null) 
        {
        	logger.info(() -> "a = " + values[0]);
        	logger.info(() -> "m = " + values[1]);
        }
    }
}
