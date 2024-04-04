package com.task;

import java.util.logging.Logger;

import com.task.datastructures.StackQueue;

public class Main
{
    public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        StackQueue<Integer> stackQueue = new StackQueue<Integer>();

        stackQueue.push(1);
        stackQueue.push(4);

        logger.info(String.valueOf(stackQueue.pop()));

        stackQueue.append(3);
        stackQueue.append(2);

        logger.info(String.valueOf(stackQueue.pop()));
        logger.info(String.valueOf(stackQueue.peek()));
        logger.info(String.valueOf(stackQueue.isEmpty()));
    }
}