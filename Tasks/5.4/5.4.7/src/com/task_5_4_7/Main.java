package com.task_5_4_7;

import java.util.logging.Logger;

import com.task_5_4_7.data.GrepMatch;

public class Main 
{
	static Logger logger = Logger.getGlobal();
	
    public static void main(String[] args) 
    {
        String pattern1 = "(A|B)(C|D)";
        String pattern2 = "A(B|C)*D";
        String pattern3 = "(A*B|AC)D";

        logger.info("Pattern 1:");
        GrepMatch.match(pattern1);

        logger.info("Pattern 2:");
        GrepMatch.match(pattern2);

        logger.info("Pattern 3:");
        GrepMatch.match(pattern3);
    }
}
