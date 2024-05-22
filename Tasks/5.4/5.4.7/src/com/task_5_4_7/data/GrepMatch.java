package com.task_5_4_7.data;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_5_4_xx.RegularExpressionMatcher;

public class GrepMatch 
{
	private GrepMatch() {}
	
	static Logger logger = Logger.getGlobal();
	
    @SuppressWarnings("resource")
	public static void match (String pattern) 
    {
        String regularExpression = "(" + pattern + ")";
        RegularExpressionMatcher regularExpressionMatcher = new RegularExpressionMatcher(regularExpression);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext())
        {	
        	logger.info("Enter string");
            String text = scanner.nextLine();

            if (regularExpressionMatcher.recognizes(text))
            {
                logger.info(text);
            }
        }
    }
}