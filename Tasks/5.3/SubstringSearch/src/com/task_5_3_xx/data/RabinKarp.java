package com.task_5_3_xx.data;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RabinKarp
{
    protected String pattern;
    protected long patternHash;
    protected int patternLength;
    protected long largePrimeNumber; 
    protected int alphabetSize = 256;
    protected long rm;

    public RabinKarp(String pattern)
    {
        if (pattern == null)
        {
            throw new IllegalArgumentException();
        }
        
        this.pattern = pattern;
        patternLength = pattern.length();
        largePrimeNumber = longRandomPrime();
        rm = 1;
        for (int i = 1; i <= patternLength - 1; ++i)
        {
            rm = (rm * alphabetSize) % largePrimeNumber;
        }
        patternHash = hash(pattern);
    }

    protected long longRandomPrime()
    {
        BigInteger prime = BigInteger.probablePrime(31, new SecureRandom());
        return prime.longValue();
    }

    protected boolean check() 
    {
        return true;
    }

    protected long hash(String key)
    {
        long hash = 0;
        for (int i = 0; i < patternLength; ++i)
        {
            hash = (hash * alphabetSize + key.charAt(i)) % largePrimeNumber;
        }
        return hash;
    }

    public int search(String text)
    {
        int textLength = text.length();
        if (textLength < patternLength)
        {
            return textLength;
        }

        long textHash = hash(text);
        if (patternHash == textHash)
        {
            return 0;
        }

        for (int i = patternLength; i < textLength; ++i)
        {
            textHash = (textHash + largePrimeNumber - rm * text.charAt(i - patternLength) % largePrimeNumber) % largePrimeNumber;
            textHash = (textHash * alphabetSize + text.charAt(i)) % largePrimeNumber;
            if (patternHash == textHash)
            {
                return i - patternLength + 1;
            }
        }
        return textLength;
    }
}