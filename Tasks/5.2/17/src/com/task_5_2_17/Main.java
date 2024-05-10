package com.task_5_2_17;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_5_2_xx.data.StringSet;
import com.task_x_x_xx.input.Input;

public class Main
{
	 static Logger logger = Logger.getLogger(Main.class.getName());
	
	 @SuppressWarnings("resource")
	private static void spellChecker(String dictionaryFileName)
	 {
	        String filePath =  System.getProperty("user.dir") + "\\" + dictionaryFileName;
	        String[] wordsInDictionary = Input.getAllStringsFromFile(filePath);
	        
	        Scanner scanner = new Scanner(System.in);
	        String input = scanner.nextLine();
	        String[] text = input.split(" ");

	        logger.info("Words not in the dictionary:");

	        if (wordsInDictionary == null)
	        {
	            for (String word : text)
	            {
	                logger.info(word);
	            }
	            return;
	        }
	        StringSet stringSet = new StringSet();
	        for (String word : wordsInDictionary)
	        {
	            stringSet.add(word);
	        }

	        for (String word : text)
	        {
	            if (!stringSet.contains(word))
	            {
	                logger.info(word);
	            }
	        }
	    }
	 
	    public static void main(String[] args)
	    {
	        String fileName = args[0];
	        spellChecker(fileName);
	    }
}
