package com.task_5_2_9;

import java.util.StringJoiner;
import java.util.logging.Logger;

import com.task_5_2_9.data.TSTExtended;

public class Main
{
	public static void main(String[] args)
	{
		Logger logger = Logger.getLogger(Main.class.getName());
        TSTExtended<Integer> tstExtended = new TSTExtended<>();

        tstExtended.put("Rene", 0);
        tstExtended.put("Re", 1);
        tstExtended.put("Algorithms", 2);
        tstExtended.put("Algo", 3);
        tstExtended.put("Algor", 4);
        tstExtended.put("Tree", 5);
        tstExtended.put("Trie", 6);
        tstExtended.put("TST", 7);

        StringJoiner initialKeys = new StringJoiner(" ");
        for (String key : tstExtended.keys()) 
        {
            initialKeys.add(key);
        }
        logger.info(() -> "Начальные ключи: " + initialKeys.toString());

        StringJoiner keysWithPrefix1 = new StringJoiner(" ");
        for (String key : tstExtended.keysWithPrefix("A")) 
        {
            keysWithPrefix1.add(key);
        }
        logger.info(() -> "Ключи с префиксом A: " + keysWithPrefix1.toString());

        StringJoiner keysWithPrefix2 = new StringJoiner(" ");
        for (String key : tstExtended.keysWithPrefix("R"))
        {
            keysWithPrefix2.add(key);
        }
        logger.info(() -> "Ключи с префиксом R: " + keysWithPrefix2.toString());

        StringJoiner keysWithPrefix3 = new StringJoiner(" ");
        for (String key : tstExtended.keysWithPrefix("Z")) 
        {
            keysWithPrefix3.add(key);
        }
        logger.info(() -> "Ключи с префиксом Z: " + keysWithPrefix3.toString());

        StringJoiner keysThatMatch1 = new StringJoiner(" ");
        for (String key : tstExtended.keysThatMatch("Tr.e"))
        {
            keysThatMatch1.add(key);
        }
        logger.info(() -> "Ключи, соответсвующие шаблону Tr.e: " + keysThatMatch1.toString());

        StringJoiner keysThatMatch2 = new StringJoiner(" ");
        for (String key : tstExtended.keysThatMatch("R."))
        {
            keysThatMatch2.add(key);
        }
        logger.info(() -> "Ключи, соответсвующие шаблону R.: " + keysThatMatch2.toString());

        String longestPrefixOf1 = tstExtended.longestPrefixOf("Ren");
        logger.info(() -> "Самый длинный префикс Ren: " + longestPrefixOf1);

        String longestPrefixOf2 = tstExtended.longestPrefixOf("Algor");
        logger.info(() -> "Самый длинный префикс Algor: " + longestPrefixOf2);
        
        String longestPrefixOf3 = tstExtended.longestPrefixOf("Quicksort");
        logger.info(() -> "Самый длинный префикс Quicksort: " + longestPrefixOf3);
    }
}
