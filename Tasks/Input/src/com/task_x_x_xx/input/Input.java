package com.task_x_x_xx.input;

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
}
