package com.task_5_4_16;

import java.util.logging.Logger;
import com.task_5_4_16.data.RegularExpressionMatcherMultiwayOr;

public class Main
{
	static Logger logger = Logger.getGlobal();

	public static void main(String[] args)
	{
        String pattern1 = "(A|B)(C|D)*";
        RegularExpressionMatcherMultiwayOr regularExpressionMatcherMultiwayOr1 = new RegularExpressionMatcherMultiwayOr(pattern1);
        String text1 = "ACCD";
        boolean matches1 = regularExpressionMatcherMultiwayOr1.recognizes(text1);
        logger.info(() -> "Проверка 1: " + matches1);

        String pattern2 = "(.*AB((C|D|E)F)*G)";
        RegularExpressionMatcherMultiwayOr regularExpressionMatcherMultiwayOr2 = new RegularExpressionMatcherMultiwayOr(pattern2);
        String text2 = "RENEABCFDFG";
        boolean matches2 = regularExpressionMatcherMultiwayOr2.recognizes(text2);
        logger.info(() -> "Проверка 2: " + matches2);

        String text3 = "RENEABDFEFG";
        boolean matches3 = regularExpressionMatcherMultiwayOr2.recognizes(text3);
        logger.info(() -> "Проверка 3: " + matches3);

        String text4 = "RENEABCFDFEFG";
        boolean matches4 = regularExpressionMatcherMultiwayOr2.recognizes(text4);
        logger.info(() -> "Проверка 4: " + matches4);

        String text5 = "RENEABCDEFG";
        boolean matches5 = regularExpressionMatcherMultiwayOr2.recognizes(text5);
        logger.info(() -> "Проверка 5: " + matches5);

        String pattern3 = "(A|B|C|D|E|F)";
        RegularExpressionMatcherMultiwayOr regularExpressionMatcherMultiwayOr3 = new RegularExpressionMatcherMultiwayOr(pattern3);
        String text6 = "A";
        boolean matches6 = regularExpressionMatcherMultiwayOr3.recognizes(text6);
        logger.info(() -> "Проверка 6: " + matches6);

        String text7 = "E";
        boolean matches7 = regularExpressionMatcherMultiwayOr3.recognizes(text7);
        logger.info(() -> "Проверка 7: " + matches7);

        String text8 = "Z";
        boolean matches8 = regularExpressionMatcherMultiwayOr3.recognizes(text8);
        logger.info(() -> "Проверка 8: " + matches8);
    }}
