package com.task_3_3_33;

import java.util.logging.Logger;

import com.task_3_3_33.data.RedBlackBSTCertification;
import com.task_3_3_xx.data.RedBlackBST;

public class Main
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) 
	{
		Logger logger = Logger.getLogger(Main.class.getName());
        RedBlackBSTCertification<Integer, String> redBlackBSTCertification = new RedBlackBSTCertification<>();

        RedBlackBST.Node root = new RedBlackBST().new Node(10, "Value 10", false, 7);
        root.setLeft(new RedBlackBST().new Node(5, "Value 5", true, 4));
        root.getLeft().setLeft(new RedBlackBST().new Node(2, "Value 2", false, 1));
        root.getLeft().setRight(new RedBlackBST().new Node(9, "Value 9", false, 2));
        root.getLeft().getRight().setLeft(new RedBlackBST().new Node(7, "Value 7", true, 1));

        root.setRight(new RedBlackBST().new Node(14, "Value 14", false, 2));
        root.getRight().setLeft(new RedBlackBST().new Node(11, "Value 11", true, 1));

        logger.info("Тест 1");
        logger.info(() -> redBlackBSTCertification.is23(root) + "");
        logger.info(() -> redBlackBSTCertification.isBalanced(root, 2) + "");
        logger.info(() -> redBlackBSTCertification.isBST(root, null, null) + "");
        logger.info("");

        RedBlackBST.Node root2 = new RedBlackBST().new Node(120, "Value 120", false, 7);
        root2.setLeft(new RedBlackBST().new Node(15, "Value 15", true, 4));
        root2.getLeft().setLeft(new RedBlackBST().new Node(12, "Value 12", false, 1));
        root2.getLeft().setRight(new RedBlackBST().new Node(19, "Value 19", false, 2));
        root2.getLeft().getRight().setLeft(new RedBlackBST().new Node(11, "Value 11", true, 1));

        root2.setRight(new RedBlackBST().new Node(124, "Value 124", false, 2));
        root2.getRight().setLeft(new RedBlackBST().new Node(121, "Value 121", true, 1));

        logger.info("Тест 2, не ДБП");
        logger.info(() -> redBlackBSTCertification.is23(root2) + "");
        logger.info(() -> redBlackBSTCertification.isBalanced(root2, 2) + "");
        logger.info(() -> redBlackBSTCertification.isBST(root2, null, null) + "");
        logger.info("");

        RedBlackBST.Node root3 = new RedBlackBST().new Node(210, "Value 210", false, 7);
        root3.setLeft(new RedBlackBST().new Node(25, "Value 25", true, 4));
        root3.getLeft().setLeft(new RedBlackBST().new Node(22, "Value 22", false, 1));
        root3.getLeft().setRight(new RedBlackBST().new Node(29, "Value 29", false, 2));
        root3.getLeft().getRight().setLeft(new RedBlackBST().new Node(27, "Value 27", true, 1));

        root3.setRight(new RedBlackBST().new Node(214, "Value 214", true, 2));
        root3.getRight().setLeft(new RedBlackBST().new Node(211, "Value 211", true, 1));

        logger.info("Тест 3, не 2-3, не сбалансированное");
        logger.info(() -> redBlackBSTCertification.is23(root3) + "");
        logger.info(() -> redBlackBSTCertification.isBalanced(root3, 2) + "");
        logger.info(() -> redBlackBSTCertification.isBST(root3, null, null) + "");
        logger.info("");
        
        RedBlackBST.Node root4 = new RedBlackBST().new Node(310, "Value 310", false, 7);
        root4.setLeft(new RedBlackBST().new Node(35, "Value 35", true, 4));
        root4.getLeft().setLeft(new RedBlackBST().new Node(32, "Value 32", true, 1));
        root4.getLeft().setRight(new RedBlackBST().new Node(39, "Value 39", false, 2));
        root4.getLeft().getRight().setLeft(new RedBlackBST().new Node(37, "Value 37", true, 1));

        root.setRight(new RedBlackBST().new Node(314, "Value 314", false, 2));
        root.getRight().setLeft(new RedBlackBST().new Node(311, "Value 311", true, 1));

        logger.info("Тест 4, не 2-3, не сбалансированное");
        logger.info(() -> redBlackBSTCertification.is23(root4) + "");
        logger.info(() -> redBlackBSTCertification.isBalanced(root4, 2) + "");
        logger.info(() -> redBlackBSTCertification.isBST(root4, null, null) + "");
        logger.info("");
    }
}
