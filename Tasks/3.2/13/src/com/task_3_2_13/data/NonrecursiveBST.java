package com.task_3_2_13.data;

import com.task_1_3_12.datastructures.Stack;
import com.task_1_3_37.datastructures.Queue;

public class NonrecursiveBST<K extends Comparable<K>, V> 
{
    private Node root;

    private class Node
    {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value)
    {
        Node node = new Node(key, value);
        if (root == null)
        {
            root = node;
            return;
        }

        Node parent = null;
        Node rootNode = root;
        while (rootNode != null)
        {
            parent = rootNode;
            int cmp = key.compareTo(rootNode.key);
            if (cmp < 0)
        	{
            	rootNode = rootNode.left;
        	}
            else if (cmp > 0) 
        	{
            	rootNode = rootNode.right;
        	}
            else 
            {
            	rootNode.value = value;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0)
    	{
    		parent.left  = node;
    	}
        else      
    	{
    		parent.right = node;
    	}
    }

    public V get(K key)
    {
        Node node = root;
        while (node != null)
        {
            int cmp = key.compareTo(node.key);
            if (cmp < 0)
        	{
        		node = node.left;
        	}
            else if (cmp > 0)
        	{
            	node = node.right;
        	}
            else
            {
            	return node.value;
            }
        }
        return null;
    }

    public Iterable<K> keys()
    {
        Stack<Node> stack = new Stack<>();
        Queue<K> queue = new Queue<>();
        Node node = root;
        while (node != null || !stack.isEmpty())
        {
            if (node != null)
            {
                stack.push(node);
                node = node.left;
            }
            else
            {
            	node = stack.pop();
                queue.enqueue(node.key);
                node = node.right;
            }
        }
        return queue;
    }
}
