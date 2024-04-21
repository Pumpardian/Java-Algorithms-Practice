package com.task_3_3_42;

import java.security.SecureRandom;
import java.util.logging.Logger;

import com.task_3_3_xx.data.RedBlackBST;

public class Main 
{
	static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) 
	{
		logger.info("Процесс расчёта может занять некоторое время...");
		SecureRandom random = new SecureRandom();
        
        int[] treeSizes = {10000, 100000, 1000000};
        int numberOfExperiments = 100;

        for (int size = 0; size < treeSizes.length; ++size)
        {
            int totalNodesCount = treeSizes[size] * numberOfExperiments;
            int totalRedNodesCount = 0;

            for (int t = 0; t < numberOfExperiments; ++t)
            {
                int treeSize = treeSizes[size];

                RedBlackBST<Integer, Integer> redBlackBST = new RedBlackBST<>();
                for (int i = 0; i < treeSize; ++i) 
                {
                    int randomKey = random.nextInt(Integer.MAX_VALUE);
                    redBlackBST.put(randomKey, randomKey);
                }

                totalRedNodesCount += countRedNodes(redBlackBST);
            }

            double percentageOfRedNodes = ((double) totalRedNodesCount) / ((double) totalNodesCount) * 100;
            printResults(treeSizes[size], percentageOfRedNodes);
        }
    }

    private static int countRedNodes(RedBlackBST<Integer, Integer> redBlackBST)
    {
        if (redBlackBST.isEmpty()) 
        {
            return 0;
        }

        return countRedNodes(redBlackBST.getRoot());
    }

    @SuppressWarnings("rawtypes")
	private static int countRedNodes(RedBlackBST.Node node) 
    {
        if (node == null) 
        {
            return 0;
        }

        int redNodeCount = node.isColor() == RedBlackBST.RED ? 1 : 0;

        return redNodeCount + countRedNodes(node.getLeft()) + countRedNodes(node.getRight());
    }

    private static void printResults(int treeSize, double percentageOfRedNodes) 
    {
        logger.info(() -> "Размер дерева: " + treeSize + " | Процент красных нодов: " + percentageOfRedNodes);
    }
}
