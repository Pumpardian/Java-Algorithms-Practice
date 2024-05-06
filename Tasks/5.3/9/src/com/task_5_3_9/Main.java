package com.task_5_3_9;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_5_3_9.data.BoyerMooreSearch;

public class Main
{
	static Logger logger = Logger.getGlobal();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
        logger.info("Введите строку");
        String text = scanner.nextLine();
        logger.info("Введите шаблон");
        String pattern = scanner.nextLine();
        
        BoyerMooreSearch boyerMooreSearch = new BoyerMooreSearch(pattern);
        int count = boyerMooreSearch.count(text);
        logger.info(() -> "Count: " + count);
        boyerMooreSearch.searchAll(text);
    }
}
