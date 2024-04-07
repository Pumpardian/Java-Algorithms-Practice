package com.task_1_3_32;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_1_3_32.datastructures.StackQueue;

public class Main
{
    public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        StackQueue<String> stackQueue = new StackQueue<>();

        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        logger.info("Введите элементы стеко-очереди (push) (ctrl + D, чтобы закончить):");
        while (scanner.hasNextLine()) 
        {
        	stackQueue.push(scanner.nextLine());
        }
		

        logger.info(() -> "" + stackQueue.pop());

        
        logger.info("Введите элементы стеко-очереди (append) (ctrl + D, чтобы закончить):");
        while (scanner.hasNextLine()) 
        {
        	stackQueue.append(scanner.nextLine());
        }

        logger.info(() -> "" + stackQueue.pop());
        logger.info(() -> "" + stackQueue.peek());
        logger.info(() -> "" + stackQueue.isEmpty());
    }
}