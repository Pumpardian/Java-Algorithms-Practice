package com.task_5_2_14;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_5_2_xx.data.TernarySearchTrie;

public class Main 
{
    public static int countUniqueSubstrings(String text, int substringLength)
    {
        TernarySearchTrie<Integer> ternarySearchTrie = new TernarySearchTrie<>();

        int maxIndex = text.length() - substringLength + 1;
        for (int i = 0; i < maxIndex; ++i) 
        {
            String substring = text.substring(i, i + substringLength);
            ternarySearchTrie.put(substring, 0);
        }
        return ternarySearchTrie.size();
    }
    
    @SuppressWarnings("resource")
	public static void main(String[] args)
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
    	Scanner scanner = new Scanner(System.in);
    	
    	logger.info("Введите длину подстроки");
        int substringLength = scanner.nextInt();

    	logger.info("Введите строку");
        String text = scanner.next();
        logger.info(() -> "Кол-во уникальных подстрок с длинной " + substringLength + ": " + countUniqueSubstrings(text, substringLength));
    }
}
