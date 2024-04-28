package com.task_3_4_21;

import java.security.SecureRandom;
import java.util.logging.Logger;

import com.task_3_4_21.data.LinearProbingHashTableAvgSearchMissCost;

public class Main
{
    public static void main(String[] args) 
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        LinearProbingHashTableAvgSearchMissCost<Integer, Integer> linearProbingHashTableAvgSearchMissCost = new LinearProbingHashTableAvgSearchMissCost<>(1000000);

        for (int i = 0; i < 500000; i++) 
        {
        	SecureRandom random = new SecureRandom();
            int randomKey = random.nextInt(Integer.MAX_VALUE);
            linearProbingHashTableAvgSearchMissCost.put(randomKey, randomKey);
        }

        logger.info(() -> "Average cost of search miss by load factor: %.2f\n" + linearProbingHashTableAvgSearchMissCost.getAverageCostOfSearchMissByLoadFactor());
        logger.info(() -> "Average cost of search miss by cluster sizes: %.2f\n" + linearProbingHashTableAvgSearchMissCost.getAverageCostOfSearchMissByClusterSizes());
    }
}
