package com.task_2_5_16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import com.task_x_x_xx.input.Input;

public class California
{
    public static final Comparator<String> ORDER = new CandidateComparator();

    private static class CandidateComparator implements Comparator<String>
    {
        private static String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
        public int compare(String a, String b)
        {
            int size = Math.min(a.length(), b.length());
            for (int i = 0; i < size; i++) {
                int aIndex = order.indexOf(a.charAt(i));
                int bIndex = order.indexOf(b.charAt(i));
                if (aIndex < bIndex)
            	{
            		return -1;
            	}
                else if (aIndex > bIndex)
            	{
            		return 1;
            	}
            }
            return a.length() - b.length();
        }
    }
    
    public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(California.class.getName());
    	List<String> input = new ArrayList<>();
    	Input.scan(logger, input);
        String[] candidates = input.toArray(new String[0]);
        int n = candidates.length;
    	for (int i = 0; i < n; ++i)
    	{
    		candidates[i] = candidates[i].toUpperCase();
    	}
        Arrays.sort(candidates, California.ORDER);
        for (int i = 0; i < n; i++)
        {
            logger.info(candidates[i]);
        }
    }
}
