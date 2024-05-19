package com.task_5_4_17;

import java.util.logging.Logger;

import com.task_5_4_17.data.RegularExpressionMatcherWildcard;

public class Main
{
	static Logger logger = Logger.getGlobal();
	
	public static void main(String[] args)
	{
        String pattern1 = ".*NEEDLE.*";
        RegularExpressionMatcherWildcard regularExpressionMatcherWildcard1 = new RegularExpressionMatcherWildcard(pattern1);
        String text1 = "A HAYSTACK NEEDLE IN";
        boolean matches1 = regularExpressionMatcherWildcard1.recognizes(text1);
        logger.info(() -> "Проверка 1: " + matches1);

        String pattern2 = "R.N.1.3";
        RegularExpressionMatcherWildcard regularExpressionMatcherWildcard2 = new RegularExpressionMatcherWildcard(pattern2);
        String text2 = "RENE123";
        boolean matches2 = regularExpressionMatcherWildcard2.recognizes(text2);
        logger.info(() -> "Проверка 2: " + matches2);

        String text3 = "RRNN193";
        boolean matches3 = regularExpressionMatcherWildcard2.recognizes(text3);
        logger.info(() -> "Проверка 3: " + matches3);

        String text4 = "RENE333";
        boolean matches4 = regularExpressionMatcherWildcard2.recognizes(text4);
        logger.info(() -> "Проверка 4: " + matches4);
    }
}
