package com.task_5_3_27.data;

public class KnuthMorrisPrattTandemRepeat
{
    private String pattern;
    private int[][] dfa;
    private int baseStringLength;
    private int tandemRepeat;

    public KnuthMorrisPrattTandemRepeat(String baseString, String text)
    {
        if (baseString == null || baseString.length() == 0) 
        {
            throw new IllegalArgumentException();
        }
        if (text == null)
        {
            throw new IllegalArgumentException();
        }
        
        StringBuilder pat = new StringBuilder();
        int maxNumberOfRepeats = text.length() / baseString.length();
        for (int i = 0; i < maxNumberOfRepeats; ++i) 
        {
            pat.append(baseString);
        }

        this.pattern = pat.toString();
        int patternLength = pat.length();
        int alphabetSize = 256;
        baseStringLength = baseString.length();
        tandemRepeat = -1;

        dfa = new int[alphabetSize][patternLength];
        dfa[pat.charAt(0)][0] = 1;

        int restartState = 0;
        for (int i = 1; i < patternLength; ++i)
        {
            for (int j = 0; j < alphabetSize; ++j)
            {
                dfa[j][i] = dfa[j][restartState];
            }
            dfa[pattern.charAt(i)][i] = i + 1;
            restartState = dfa[pattern.charAt(i)][restartState];
        }
        computeTandemRepeat(text);
    }

    private void computeTandemRepeat(String text) 
    {
        int textIndex;
        int patternIndex;
        int maxPatternIndexMatched = baseStringLength;

        for (textIndex = 0, patternIndex = 0; textIndex < text.length() && patternIndex < pattern.length(); ++textIndex)
        {
            patternIndex = dfa[text.charAt(textIndex)][patternIndex];

            if (patternIndex % baseStringLength == 0 && patternIndex > maxPatternIndexMatched)
            {
                tandemRepeat = textIndex - patternIndex + 1;
                maxPatternIndexMatched = patternIndex;
            }
        }
    }

    public int findTandemRepeat() 
    {
        return tandemRepeat;
    }
}
