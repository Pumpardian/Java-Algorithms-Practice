package com.task_5_1_7;

import java.util.StringJoiner;
import java.util.logging.Logger;
import com.task_1_3_37.datastructures.Queue;

public class Main
{
	@SuppressWarnings("unchecked")
	public static void keyIndexedCountWithQueue(String[] array, int stringsLength) 
    {
        int alphabetSize = 256;

        Queue<String>[] count = new Queue[alphabetSize + 1];

        for (int r = 0; r < count.length; ++r)
        {
            count[r] = new Queue<>();
        }

        for (int digit = stringsLength - 1; digit >= 0; --digit) 
        {
            for (int i = 0; i < array.length; ++i) 
            {
                int digitIndex = array[i].charAt(digit);
                count[digitIndex].enqueue(array[i]);
            }

            int indexArray = 0;
            for (int r = 0; r < count.length; ++r) 
            {
                while (!count[r].isEmpty()) 
                {
                    String string = count[r].dequeue();
                    array[indexArray++] = string;
                }
            }
        }
    }

    public static void main(String[] args) 
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        String[] array = 
        	{	"4PGC938",
                "2IYE230",
                "3CIO720",
                "1ICK750",
                "1OHV845",
                "4JZY524",
                "1ICK750",
                "3CIO720",
                "1OHV845",
                "2RLA629",
                "2RLA629",
                "3ATW723"};
        keyIndexedCountWithQueue(array, 7);

        StringJoiner sortedArray = new StringJoiner(" ");
        for (String string : array)
        {
            sortedArray.add(string);
        }
        logger.info(() -> "" + sortedArray);
    }
}
