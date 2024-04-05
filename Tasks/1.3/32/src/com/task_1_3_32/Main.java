package com.task_1_3_32;

import java.util.logging.Logger;
import com.task_1_3_xx.datastructures.StackQueue;

public class Main
{
    public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        StackQueue<Integer> stackQueue = new StackQueue<>();

        stackQueue.push(1);
        stackQueue.push(4);

        logger.info(() -> "" + stackQueue.pop());

        stackQueue.append(3);
        stackQueue.append(2);

        logger.info(() -> "" + stackQueue.pop());
        logger.info(() -> "" + stackQueue.peek());
        logger.info(() -> "" + stackQueue.isEmpty());
    }
}