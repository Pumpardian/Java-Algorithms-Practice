package com.task_2_5_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.task_2_5_8.data.Record;
import com.task_x_x_xx.input.Input;

public class Frequency
{
	public static void main(String[] args)
	{
		Logger logger = Logger.getLogger(Frequency.class.getName());
        List<String> list = new ArrayList<>();
        Input.scan(logger, list);
        String[] array = list.toArray(new String[0]);
        int size = array.length;
        Arrays.sort(array);
        
        Record[] records = new Record[size];
        String word = array[0];
        int freq = 1;
        int m = 0;
        for (int i = 1; i < size; ++i)
        {
            if (!array[i].equals(word))
            {
                records[m++] = new Record(word, freq);
                word = array[i];
                freq = 0;
            }
            ++freq;
        }
        records[m++] = new Record(word, freq);
        
        Arrays.sort(records, 0, m);
        for (int i = m - 1; i >= 0; --i)
        {
        	int x = i;
            logger.info(() -> "" + records[x]);
        }
	}
}
