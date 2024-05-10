package com.task_x_x_xx.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Input
{
	private static Scanner scanner = new Scanner(System.in);
	
	private Input() {}
	
	public static void scan(Logger logger, List<String> list)
	{
	    Scanner stdin = new Scanner(System.in);
    	logger.info("Введите значения (Ctrl + D чтобы закончить)");
	    do
	    {	    	
	        if (stdin.hasNext())
	        {
	            list.add(stdin.nextLine());
	        }
	        else
	        {
	            break;
	        }
	    } while (true);
	    stdin.close();
	}
	
	public static String readAll()
	{
		if (!scanner.hasNextLine())
		{
			return "";
		}
		return scanner.useDelimiter(Pattern.compile("\\A")).next();
	}
	
    public static String[] getAllStringsFromFile(String filePath)
    {
        List<String> lines;

        try
        {
            lines = Files.readAllLines(Paths.get(filePath));
        }
        catch (IOException e) 
        {
            return new String[0];
        }

        List<String> wordsList = new ArrayList<>();

        for (String line : lines) 
        {
            String[] wordsInCurrentLine = line.split(" ");
            for (String wordInCurrentLine : wordsInCurrentLine) 
            {
                if (wordInCurrentLine.equals("")) 
                {
                    continue;
                }
                wordsList.add(wordInCurrentLine);
            }
        }
        String[] words = new String[wordsList.size()] ;
        wordsList.toArray(words);

        return words;
    }
}
