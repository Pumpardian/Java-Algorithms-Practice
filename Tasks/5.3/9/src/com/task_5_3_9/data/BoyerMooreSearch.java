package com.task_5_3_9.data;

import java.util.logging.Logger;

import com.task_5_3_xx.data.BoyerMoore;

public class BoyerMooreSearch extends BoyerMoore
{
	Logger logger = Logger.getGlobal();
	
    public BoyerMooreSearch(String pattern)
    {
        super(pattern);
    }
    
    public int count(String text)
    {
        int count = 0;
        int occurrenceIndex = searchFromIndex(text, 0);
        while (occurrenceIndex != text.length())
        {
            ++count;
            occurrenceIndex = searchFromIndex(text, occurrenceIndex + 1);
        }
        return count;
    }
    
    public void searchAll(String text) 
    {
        int occurrenceIndex = searchFromIndex(text, 0);
        if (occurrenceIndex == text.length())
        {
            logger.info("Нет совпадений");
            return;
        }
        while (occurrenceIndex != text.length())
        {
        	int x = occurrenceIndex;
        	logger.info(() -> "Шаблон найден по индексу " + x);
            occurrenceIndex = searchFromIndex(text, occurrenceIndex + 1);
        }
    }
    
    protected int searchFromIndex(String text, int textStartIndex)
    {
        int textLength = text.length();
        int patternLength = pattern.length();
        int skip;
        for (int i = textStartIndex; i <= textLength - patternLength; i += skip) 
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