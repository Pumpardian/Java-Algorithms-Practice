package com.task_1_3_14;

import java.util.Scanner;
import java.util.logging.Logger;
import com.task_1_3_14.datastructures.ResizingArrayQueueOfStrings;

public class Main 
{
    public static void main(String[] args) 
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
		ResizingArrayQueueOfStrings resizingArrayQueueOfStrings = new ResizingArrayQueueOfStrings(3);
		
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        logger.info("Введите элементы очереди (ctrl + D, чтобы закончить):");
        while (scanner.hasNextLine()) 
        {
        	resizingArrayQueueOfStrings.enqueue(scanner.nextLine());
        }
		
		logger.info(() -> "" + resizingArrayQueueOfStrings.dequeue());
		logger.info(() -> "" + resizingArrayQueueOfStrings.dequeue());
    }
}
