package com.task_2_5_24;

import java.util.Scanner;
import java.util.logging.Logger;
import com.task_2_5_24.datastructures.StableMinPQ;
import com.task_2_5_24.datastructures.Tuple;

public class Main
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		Logger logger = Logger.getLogger(Main.class.getName());
        StableMinPQ<Tuple> pq = new StableMinPQ<>();
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length; ++i)
        {
            pq.insert(new Tuple(strings[i], i));
        }

        while (!pq.isEmpty())
        {
            logger.info(() -> "" + pq.delMin());
        }
        logger.info(() -> "");
    }

}
