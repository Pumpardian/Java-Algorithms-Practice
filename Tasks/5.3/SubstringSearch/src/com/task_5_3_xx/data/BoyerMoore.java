package com.task_5_3_xx.data;

public class BoyerMoore
{
    protected int[] right;
    protected String pattern;

    public BoyerMoore(String pattern)
    {
        if (pattern == null)
        {
            throw new IllegalArgumentException();
        }

        this.pattern = pattern;
        int alphabetSize = 256;
        right = new int[alphabetSize];
        for (int i = 0; i < alphabetSize; ++i)
        {
            right[i] = -1;
        }
        for (int i = 0; i < pattern.length(); ++i)
        {
            right[pattern.charAt(i)] = i;
        }
    }

    public int search(String text)
    {
        int textLength = text.length();
        int patternLength = pattern.length();
        int skip;
        for (int i = 0; i <= textLength - patternLength; i += skip)
        {
        	skip = 0;
            for (int j = patternLength - 1; j >= 0; --j)
            {
                if (pattern.charAt(j) != text.charAt(i + j)) 
                {
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0)
            {
                return i;
            }
        }
        return textLength;
    }
}
