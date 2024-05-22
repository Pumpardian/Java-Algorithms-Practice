package com.task_5_4_18;

import java.util.logging.Logger;

import com.task_5_4_18.data.RegularExpressionMatcherOneOrMore;

public class Main
{
	static Logger logger = Logger.getGlobal();
	
	public static void main(String[] args)
	{
        String pattern1 = "(RE)+NE";
        RegularExpressionMatcherOneOrMore regularExpressionMatcherOneOrMore1 = new RegularExpressionMatcherOneOrMore(pattern1);
        String text1 = "RERERENE";
        boolean matches1 = regularExpressionMatcherOneOrMore1.recognizes(text1);
        logger.info(() -> "Проверка 1: " + matches1);

        String text2 = "RONE";
        boolean matches2 = regularExpressionMatcherOneOrMore1.recognizes(text2);
        logger.info(() -> "Проверка 2: " + matches2);

        String text3 = "RENE";
        boolean matches3 = regularExpressionMatcherOneOrMore1.recognizes(text3);
        logger.info(() -> "Проверка 3: " + matches3);

        String pattern2 = "ABCR+PQR";
        RegularExpressionMatcherOneOrMore regularExpressionMatcherOneOrMore2 = new RegularExpressionMatcherOneOrMore(pattern2);
        String text4 = "ABCRPQR";
        boolean matches4 = regularExpressionMatcherOneOrMore2.recognizes(text4);
        logger.info(() -> "Проверка 4: " + matches4);

        String text5 = "ABCRRRRRRRRRRRRRRPQR";
        boolean matches5 = regularExpressionMatcherOneOrMore2.recognizes(text5);
        logger.info(() -> "Проверка 5: " + matches5);

        String text6 = "ABCPQR";
        boolean matches6 = regularExpressionMatcherOneOrMore2.recognizes(text6);
        logger.info(() -> "Проверка 6: " + matches6);
    }
}
