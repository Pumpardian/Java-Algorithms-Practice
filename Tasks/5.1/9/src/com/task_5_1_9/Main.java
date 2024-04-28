package com.task_5_1_9;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

import com.task_5_1_9.data.LSDVariableLength;
import com.task_x_x_xx.input.Input;

public class Main
{
    public static void main(String[] args) 
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        LSDVariableLength leastSignificantDigitVariableLength = new LSDVariableLength();
	    List<String> list = new ArrayList<>();
	    Input.scan(logger, list);
        String[] array = list.toArray(new String[0]);
        leastSignificantDigitVariableLength.lsdSort(array);

        StringJoiner sortedArray = new StringJoiner(" ");

        for (String string : array) 
        {
            sortedArray.add(string);
        }
        logger.info(() -> "" + sortedArray);
    }
}
