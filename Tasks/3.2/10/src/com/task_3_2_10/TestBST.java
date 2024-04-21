package com.task_3_2_10;

import java.util.Scanner;
import java.util.logging.Logger;

import com.task_3_2_6.data.BST;

public class TestBST
{
	 @SuppressWarnings("resource")
	public static void main(String[] args)
	 {
		Logger logger = Logger.getLogger(TestBST.class.getName());
        logger.info("Введите ключи (разделённые пробелом)");
		Scanner scanner = new Scanner(System.in);
        String test = scanner.nextLine();
        String[] keys = test.split(" ");
        int size = keys.length;
        BST<String, Integer> st = new BST<>();
        for (int i = 0; i < size; i++)
        {
            st.put(keys[i], i);
        }
        
        logger.info(() -> "size = " + st.size());
        logger.info(() -> "min  = " + st.min());
        logger.info(() -> "max  = " + st.max());
        logger.info("");

        logger.info("Тестирование keys()");
        for (String s : st.keys())
        {
        	logger.info(() -> s + " " + st.get(s));
        }
        logger.info("");

        logger.info("Тестирование select");
        for (int i = 0; i < st.size(); i++)
        {
        	int x = i;
            logger.info(() -> x + " " + st.select(x));
        }
        logger.info("");

        logger.info("Тестирование: rank floor flor2 ceil");
        for (char i = 'A'; i <= 'X'; i++) 
        {
            String s = i + "";
            logger.info(() -> s + st.rank(s) + st.floor(s) + st.floor2(s) + st.ceiling(s));
        }
        logger.info("");

        String[] from = { "A", "Z", "X", "0", "B", "C" };
        String[] to   = { "Z", "A", "X", "Z", "G", "L" };
        logger.info("Поиск по диапозону");
        for (int i = 0; i < from.length; i++)
        {
        	int x = i;
            logger.info(() -> from[x] + to[x] + st.size(from[x], to[x]));
            for (String s : st.keys(from[i], to[i]))
            {
                logger.info(() -> s + " ");
            }
            logger.info("");
        }
        logger.info("");

        for (int i = 0; i < st.size() / 2; i++)
        {
            st.deleteMin();
        }
        
        logger.info(() -> "После удаления наименьших" + st.size() / 2 + " ключей");
        for (String s : st.keys())
        {
            logger.info(() -> s + " " + st.get(s));
        }
        logger.info("");
        while (!st.isEmpty())
        {
            st.delete(st.select(st.size() / 2));
        }
        
        logger.info("После удаления оставшихся ключей");
        for (String s : st.keys())
        {
            logger.info(() -> s + " " + st.get(s));
        }
        logger.info("");

        logger.info("После добавления ключей обратно");
        for (int i = 0; i < size; i++)
        {
            st.put(keys[i], i);
        }
        for (String s : st.keys())
        {
            logger.info(() -> s + " " + st.get(s));
        }
        logger.info("");
    }
}
