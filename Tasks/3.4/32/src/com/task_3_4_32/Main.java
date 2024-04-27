package com.task_3_4_32;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main
{
    private static List<String> generateStringsInput(int n) 
    {
        String[] values = {"Aa", "BB"};

        List<String> strings = new ArrayList<>();
        generateStrings(n, 0, strings, "", values);

        return strings;
    }

    private static void generateStrings(int n, int currentIndex, List<String> strings, String currentString, String[] values) {
        if (currentIndex == n) 
        {
            strings.add(currentString);
            return;
        }

        for (String value : values) 
        {
            String newValue = currentString + value;
            int newIndex = currentIndex + 1;

            generateStrings(n, newIndex, strings, newValue, values);
        }
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) 
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        logger.info("Введите n (большие числа могут привести к нехватке памяти)");
    	Scanner scanner = new Scanner(System.in);
    	int n = scanner.nextInt();
        List<String> hashAttackInput = generateStringsInput(n);

        for (String string : hashAttackInput) 
        {
            logger.info(string);
        }
    }
}
