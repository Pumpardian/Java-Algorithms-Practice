package com.task_5_3_27;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_5_3_27.data.KnuthMorrisPrattTandemRepeat;

public class Main
{
	static Logger logger = Logger.getGlobal();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		logger.info("Введите базовую строку");
		String baseString = scanner.nextLine();
		logger.info("Введите строку");
        String text = scanner.nextLine();

        KnuthMorrisPrattTandemRepeat knuthMorrisPrattTandemRepeat = new KnuthMorrisPrattTandemRepeat(baseString, text);
        int tandemRepeat = knuthMorrisPrattTandemRepeat.findTandemRepeat();
        logger.info(() -> "Кратное повторение: " + tandemRepeat);
    }
}
