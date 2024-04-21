package com.task_3_2_6;

import java.util.logging.Logger;
import com.task_3_2_6.data.BST;

public class Main 
{
	static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args)
	{
        testRecursiveHeightMethod();
        testNonRecursiveHeightMethod();
	}
	
	private static void testRecursiveHeightMethod()
	{
        BST<Integer, Integer> binarySearchTree = new BST<>();

        logger.info("Тесты рекурсивного метода");
        logger.info(() -> "#1: " + binarySearchTree.heightRecursive());

        binarySearchTree.put(0, 0);
        binarySearchTree.put(1, 1);
        binarySearchTree.put(2, 2);
        binarySearchTree.put(3, 3);

        logger.info(() -> "#2: " + binarySearchTree.heightRecursive());

        binarySearchTree.put(-1, -1);
        binarySearchTree.put(-2, -2);

        logger.info(() -> "#3: " + binarySearchTree.heightRecursive());

        binarySearchTree.put(-10, -10);
        binarySearchTree.put(-7, -7);

        logger.info(() -> "#4: " + binarySearchTree.heightRecursive());

        binarySearchTree.delete(-7);
        logger.info(() -> "#5: " + binarySearchTree.heightRecursive());

        binarySearchTree.deleteMin();
        binarySearchTree.deleteMax();
        logger.info(() -> "#6: " + binarySearchTree.heightRecursive());
    }

    private static void testNonRecursiveHeightMethod()
    {
        BST<Integer, Integer> binarySearchTree = new BST<>();

        logger.info("\nТесты метода с дополнительным полем");
        logger.info(() -> "#1: " + binarySearchTree.heightConstant());

        binarySearchTree.put(0, 0);
        binarySearchTree.put(1, 1);
        binarySearchTree.put(2, 2);
        binarySearchTree.put(3, 3);

        logger.info(() -> "#2: " + binarySearchTree.heightConstant());

        binarySearchTree.put(-1, -1);
        binarySearchTree.put(-2, -2);

        logger.info(() -> "#3: " + binarySearchTree.heightConstant());

        binarySearchTree.put(-10, -10);
        binarySearchTree.put(-7, -7);

        logger.info(() -> "#4: " + binarySearchTree.heightConstant());

        binarySearchTree.delete(-7);
        logger.info(() -> "#5: " + binarySearchTree.heightConstant());

        binarySearchTree.deleteMin();
        binarySearchTree.deleteMax();
        logger.info(() -> "#6: " + binarySearchTree.heightConstant());
    }
}
