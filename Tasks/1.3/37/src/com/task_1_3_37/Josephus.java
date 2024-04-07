package com.task_1_3_37;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_1_3_37.datastructures.Queue;

public class Josephus 
{
	public static void main(String[] args)
	{
    	Logger logger = Logger.getLogger(Josephus.class.getName());

        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        logger.info("Введите m (номер в очереди)");
        int m = scanner.nextInt();
        logger.info("Введите n (кол-во людей)");
        int n = scanner.nextInt();
		
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; ++i)
        {
            queue.enqueue(i);
        }

        while (n > 0)
        {
            for (int i = 1; i < m; ++i)
            {
                queue.enqueue(queue.dequeue());
            }
            logger.info(() -> "" + queue.dequeue());
            --n;
        }
        logger.info("");
    }
}
