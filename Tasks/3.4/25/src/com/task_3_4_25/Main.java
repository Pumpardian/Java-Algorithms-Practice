package com.task_3_4_25;

import java.util.Date;
import java.util.logging.Logger;

import com.task_3_4_25.data.Transaction;

public class Main 
{
    @SuppressWarnings("deprecation")
	public static void main(String[] args) 
    {
    	Logger logger = Logger.getLogger(Main.class.getName());
        Transaction transaction = new Transaction("Test", new Date(1, 10, 5), 1000);
        logger.info(() -> transaction.hashCode() + "");
        logger.info(() -> transaction.hashCode() + "");
        logger.info(() -> transaction.hashCode() + "");
    }
}
