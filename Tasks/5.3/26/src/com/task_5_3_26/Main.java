package com.task_5_3_26;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_5_3_xx.data.RabinKarp;

public class Main 
{
	static Logger logger = Logger.getGlobal();
	
	public static boolean isCyclicRotation(String string1, String string2)
	{
        if (string1.length() != string2.length())
        {
            return false;
        }
        String concatenatedString = string1 + string1;
        RabinKarp rabinKarp = new RabinKarp(string2);
        return rabinKarp.search(concatenatedString) != concatenatedString.length();
    }

    @SuppressWarnings("resource")
	public static void main(String[] args)
    {
		Scanner scanner = new Scanner(System.in);
        logger.info("Введите строку");
        String str1 = scanner.nextLine();
        logger.info("Введите шаблон");
        String str2 = scanner.nextLine();
        
        boolean check = isCyclicRotation(str1, str2);
        logger.info(() -> "Циклическое повторение : " + check);
    }
}
