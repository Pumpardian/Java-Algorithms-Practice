package com.task_3_4_21.data;

public class LinearProbingHashTableAvgSearchMissCost<K, V> extends LinearProbingHashST<K, V> 
{
    public LinearProbingHashTableAvgSearchMissCost(int size)
    {
        super(size);
    }

    public double getAverageCostOfSearchMissByLoadFactor()
    {
        double loadFactor = getLoadFactor();
        return 0.5 * (1 + (1 / Math.pow(1 - loadFactor, 2)));
    }

    public double getAverageCostOfSearchMissByClusterSizes() 
    {
        int clusterSize = 0;
        int clusterSizeSquareSum = 0;
        
        for (int i = 0; i < capacity; ++i)
        {
            if (keys[i] != null)
            {
                ++clusterSize;
            }
            else
            {
                clusterSizeSquareSum += clusterSize * clusterSize;
                clusterSize = 0;
            }
        }

        if (clusterSize != 0)
        {
            clusterSizeSquareSum += clusterSize * clusterSize;
        }
        return 1 + ((double) (size + clusterSizeSquareSum)) / (capacity * 2);
    }
}
