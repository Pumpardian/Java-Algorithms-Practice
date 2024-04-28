package com.task_5_1_18;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main
{
	public static String[] randomDecimalKeys(int numberOfStrings, int numberOfDigits)
	{
        String[] strings = new String[numberOfStrings];
        SecureRandom random = new SecureRandom();
        
        for (int string = 0; string < numberOfStrings; ++string)
        {
            StringBuilder currentString = new StringBuilder();

            for (int digit = 0; digit < numberOfDigits; ++digit)
            {
                int digitValue = random.nextInt(10);
                currentString.append(digitValue);
            }

            strings[string] = currentString.toString();
        }
        return strings;
    }

    @SuppressWarnings("resource")
	public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
    	Scanner scanner = new Scanner(System.in);
    	
    	logger.info("Введите кол-во строк");
        int numberOfStrings = scanner.nextInt();
    	logger.info("Введите кол-во разрядов");
        int numberOfDigits = scanner.nextInt();

        String[] randomStrings = randomDecimalKeys(numberOfStrings, numberOfDigits);
        logger.info("Сгенерированные строки:");

        for (String randomString : randomStrings)
        {
            logger.info(randomString);
        }
    }
}
