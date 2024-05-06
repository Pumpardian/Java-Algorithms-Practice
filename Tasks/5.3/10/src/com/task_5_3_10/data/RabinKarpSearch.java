package com.task_5_3_10.data;

import java.util.logging.Logger;

import com.task_5_3_xx.data.RabinKarp;

public class RabinKarpSearch extends RabinKarp
{
	Logger logger = Logger.getGlobal();
	
    public RabinKarpSearch(String pattern)
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
            if (occurrenceIndex + 1 >= text.length())
            {
                break;
            }
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
            if (occurrenceIndex + 1 >= text.length()) 
            {
                break;
            }
            occurrenceIndex = searchFromIndex(text, occurrenceIndex + 1);
        }
    }
    
    protected int searchFromIndex(String text, int textStartIndex)
    {
        String eligibleText = text.substring(textStartIndex);
        int textLength = eligibleText.length();
        if (textLength < patternLength)
        {
            return textStartIndex + textLength;
        }
        long textHash = hash(eligibleText);
        if (patternHash == textHash)
        {
            return textStartIndex;
        }
        for (int i = patternLength; i < textLength; ++i)
        {
        	textHash = (textHash + largePrimeNumber - rm * eligibleText.charAt(i - patternLength) % largePrimeNumber) % largePrimeNumber;
            textHash = (textHash * alphabetSize + eligibleText.charAt(i)) % largePrimeNumber;
            int offset = i - patternLength + 1;
            if (patternHash == textHash)
            {
                return textStartIndex + offset;
            }
        }
        return textStartIndex + textLength;
    }

}