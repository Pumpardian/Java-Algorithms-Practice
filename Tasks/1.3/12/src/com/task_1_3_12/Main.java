package com.task_1_3_12;

import java.util.logging.Logger;

import com.task_1_3_12.datastructures.Stack;

class Main
{
    public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        Stack<String> stack = new Stack<>();

        stack.push("1");
        stack.push("4");
        stack.push("3");
        stack.push("2");

        Stack<String> copy = Stack.copy(stack);
        
        logger.info(stack.pop());
        logger.info(stack.pop());
        logger.info(String.valueOf(stack.isEmpty()));
        
        for (String e : copy)
        {
        	logger.info(e);
        }
    }
}