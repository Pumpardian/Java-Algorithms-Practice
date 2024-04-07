package com.task_1_3_12;

import java.util.Scanner;
import java.util.logging.Logger;
import com.task_1_3_12.datastructures.Stack;

class Main
{
    public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        Stack<String> stack = new Stack<>();

        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        logger.info("Введите элементы стека (ctrl + D, чтобы закончить):");
        while (scanner.hasNextLine()) 
        {
            stack.push(scanner.nextLine());
        }

        Stack<String> copy = Stack.copy(stack);
        
        logger.info(() -> "" + stack.pop());
        logger.info(() -> "" + stack.pop());
        logger.info(() -> "" + stack.isEmpty());
        
        for (String e : copy)
        {
        	logger.info(e);
        }
    }
}