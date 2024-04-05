package com.task_1_3_37;

import java.util.logging.Logger;
import com.task_1_3_xx.datastructures.Queue;

public class Josephus 
{
	public static void main(String[] args)
	{
    	Logger logger = Logger.getLogger(Josephus.class.getName());
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++)
        {
            queue.enqueue(i);
        }

        while (!queue.isEmpty())
        {
            for (int i = 0; i < m-1; i++)
            {
                queue.enqueue(queue.dequeue());
            }
           logger.info(() -> "" + queue.dequeue());
        }
        logger.info("");
    }
}
