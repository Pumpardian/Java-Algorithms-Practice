package com.task_3_3_33.data;

import com.task_3_3_xx.data.RedBlackBST;

public  class RedBlackBSTCertification<K extends Comparable<K>, V> extends RedBlackBST<K, V>
{   
    public boolean is23()
    {
        return is23(getRoot());
    }

    public boolean is23(Node node)
    {
        if (node == null)
        {
            return true;
        }

        if (isRed(node.getRight())) 
        {
            return false;
        }
        if (isRed(node) && isRed(node.getLeft()))
        {
            return false;
        }

        return is23(node.getLeft()) && is23(node.getRight());
    }

    public boolean isBalanced()
    {
        int blackNodes = 0;

        Node currentNode = getRoot();
        while (currentNode != null)
        {
            if (!isRed(currentNode))
            {
                ++blackNodes;
            }

            currentNode = currentNode.getLeft();
        }

        return isBalanced(getRoot(), blackNodes);
    }

    public boolean isBalanced(Node node, int blackNodes)
    {
        if (node == null) 
        {
            return blackNodes == 0;
        }

        if (!isRed(node)) 
        {
            --blackNodes;
        }

        return isBalanced(node.getLeft(), blackNodes) && isBalanced(node.getRight(), blackNodes);
    }

    public boolean isBST() 
    {
        return isBST(getRoot(), null, null);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean isBST(Node node, Comparable low, Comparable high)
    {
        if (node == null)
        {
            return true;
        }

        if (low != null && low.compareTo(node.getKey()) >= 0)
        {
            return false;
        }
        if (high != null && high.compareTo(node.getKey()) <= 0)
        {
            return false;
        }

        return isBST(node.getLeft(), low, node.getKey()) && isBST(node.getRight(), node.getKey(), high);
    }

    public boolean isRedBlackBST() 
    {
        return isBST() && is23() && isBalanced();
    }

}
