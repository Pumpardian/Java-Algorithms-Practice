package com.task_5_2_20;

import java.util.logging.Logger;

import com.task_5_2_20.data.StringSetContainsPrefix;

public class Main 
{
	 public static void main(String[] args)
	 {
		 	Logger logger = Logger.getLogger(Main.class.getName());
	        StringSetContainsPrefix stringSetContainsPrefix = new StringSetContainsPrefix();

	        stringSetContainsPrefix.add("Rene");
	        stringSetContainsPrefix.add("Re");
	        stringSetContainsPrefix.add("Algorithms");
	        stringSetContainsPrefix.add("Algo");
	        stringSetContainsPrefix.add("Algor");
	        stringSetContainsPrefix.add("Tree");
	        stringSetContainsPrefix.add("Trie");
	        stringSetContainsPrefix.add("TST");
	        stringSetContainsPrefix.add("Trie123");
	        stringSetContainsPrefix.add("Z-Function");

	        logger.info(() -> "Contains prefix Alg: " + stringSetContainsPrefix.containsPrefix("Alg"));

	        logger.info(() -> "Contains prefix Trie123: " + stringSetContainsPrefix.containsPrefix("Trie123"));

	        logger.info(() -> "Contains prefix T: " + stringSetContainsPrefix.containsPrefix("T"));

	        logger.info(() -> "Contains prefix R: " + stringSetContainsPrefix.containsPrefix("R"));

	        logger.info(() -> "Contains prefix Algar: " + stringSetContainsPrefix.containsPrefix("Algar"));

	        logger.info(() -> "Contains prefix ZZZ: " + stringSetContainsPrefix.containsPrefix("ZZZ"));
	    }

}
