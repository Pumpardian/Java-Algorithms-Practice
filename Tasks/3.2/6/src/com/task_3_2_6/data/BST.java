package com.task_3_2_6.data;

import java.util.NoSuchElementException;
import com.task_1_3_37.datastructures.Queue;

public class BST<K extends Comparable<K>, V>
{
    private Node root;

    private class Node
    {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;
        private int height;

        public Node(K key, V value, int size)
        {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public int size()
    {
        return size(root);
    }

    private int size(Node node)
    {
        if (node == null)
    	{
    		return 0;
    	}
        else return node.size;
    }

    public boolean contains(K key)
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        return get(key) != null;
    }

    public V get(K key)
    {
        return get(root, key);
    }

    private V get(Node node, K key)
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (node == null)
    	{
    		return null;
    	}
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
    	{
    		return get(node.left, key);
    	}
        else if (cmp > 0)
    	{
    		return get(node.right, key);
    	}
        else  
    	{
    		return node.value;
    	}
    }

    public void put(K key, V val) {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (val == null)
        {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V value)
    {
        if (node == null)
    	{
    		return new Node(key, value, 1);
    	}
        int cmp = key.compareTo(node.key);
        if (cmp < 0) 
    	{
    		node.left = put(node.left,  key, value);
    	}
        else if (cmp > 0) 
    	{
    		node.right = put(node.right, key, value);
    	}
        else             
    	{
    		node.value   = value;
    	}
        node.size = 1 + size(node.left) + size(node.right);
        node.height = Math.max(heightConstant(node.left), heightConstant(node.right)) + 1;
        return node;
    }

    public void deleteMin()
    {
        if (isEmpty()) 
    	{
    		throw new NoSuchElementException();
    	}
        root = deleteMin(root);
    }

    private Node deleteMin(Node node)
    {
    	if (node == null)
    	{
    		return null;
    	}
        if (node.left == null)
    	{
    		return node.right;
    	}
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightConstant(node.left), heightConstant(node.right)) + 1;
        return node;
    }
    
    public void deleteMax()
    {
        if (isEmpty())
    	{
        	throw new NoSuchElementException();
    	}
        root = deleteMax(root);
    }

    private Node deleteMax(Node node)
    {
        if (node == null)
        {
            return null;
        }
        if (node.right == null)
    	{
    		return node.left;
    	}
        node.right = deleteMax(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightConstant(node.left), heightConstant(node.right)) + 1;
        return node;
    }

    public void delete(K key)
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        root = delete(root, key);
    }

    private Node delete(Node node, K key)
    {
        if (node == null)
    	{
    		return null;
    	}

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
    	{
    		node.left  = delete(node.left,  key);
    	}
        else if (cmp > 0)
    	{
    		node.right = delete(node.right, key);
    	}
        else
        {
            if (node.right == null)
        	{
        		return node.left;
        	}
            if (node.left  == null)
        	{
        		return node.right;
        	}
            Node tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightConstant(node.left), heightConstant(node.right)) + 1;
        return node;
    }

    public K min()
    {
        if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
        return min(root).key;
    }

    private Node min(Node node)
    {
        if (node.left == null)
    	{
    		return node;
    	}
        else
    	{
    		return min(node.left);
    	}
    }

    public K max()
    {
        if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
        return max(root).key;
    }

    private Node max(Node node)
    {
        if (node.right == null)
    	{
    		return node;
    	}
        else 
    	{
    		return max(node.right);
    	}
    }

    public K floor(K key)
    {
        Node node = floor(root, key);
        if (node == null)
    	{
    		return null;
    	}
        return node.key;
    }

    private Node floor(Node node, K key)
    {
        if (node == null)
    	{
    		return null;
    	}
        int cmp = key.compareTo(node.key);
        if (cmp == 0)
    	{
    		return node;
    	}
        else if (cmp < 0) 
    	{
    		return floor(node.left, key);
    	}
        else
        {
        	Node tmp = floor(node.right, key);
	        if (tmp != null)
	    	{
	    		return tmp;
	    	}
	        else
	        {
	        	return node;
	        }
        }
    }

    public K floor2(K key)
    {
        K node = floor2(root, key, null);
        if (node == null)
    	{
    		return null;
    	}
        return node;

    }

    private K floor2(Node node, K key, K best)
    {
        if (node == null)
    	{
    		return best;
    	}
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
    	{
    		return floor2(node.left, key, best);
    	}
        else if (cmp > 0)
    	{
    		return floor2(node.right, key, node.key);
    	}
        else 
    	{
    		return node.key;
    	}
    }

    public K ceiling(K key)
    {
        Node node = ceiling(root, key);
        if (node == null)
    	{
    		return null;
    	}
        return node.key;
    }

    private Node ceiling(Node node, K key)
    {
        if (node == null)
    	{
    		return null;
    	}
        int cmp = key.compareTo(node.key);
        if (cmp == 0)
    	{
    		return node;
    	}
        if (cmp < 0) 
        {
            Node t = ceiling(node.left, key);
            if (t != null)
        	{
        		return t;
        	}
            else
        	{
        		return node;
        	}
        }
        return ceiling(node.right, key);
    }

    public K select(int rank)
    {
        if (rank < 0 || rank >= size())
        {
            throw new IllegalArgumentException();
        }
        return select(root, rank);
    }

    private K select(Node node, int rank)
    {
        if (node == null)
    	{
    		return null;
    	}
        int leftSize = size(node.left);
        if (leftSize > rank)
    	{
    		return select(node.left,  rank);
    	}
        else if (leftSize < rank)
    	{
    		return select(node.right, rank - leftSize - 1);
    	}
        else 
    	{
    		return node.key;
    	}
    }

    public int rank(K key)
    {
        if (key == null)
        {
        	throw new IllegalArgumentException();
        }
        return rank(key, root);
    }

    private int rank(K key, Node node)
    {
        if (node == null)
    	{
    		return 0;
    	}
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
        {
        	return rank(key, node.left);
		}
        else if (cmp > 0) 
    	{
    		return 1 + size(node.left) + rank(key, node.right);
    	}
        else
    	{
    		return size(node.left);
    	}
    }

    public Iterable<K> keys()
    {
        if (isEmpty())
    	{
    		return new Queue<>();
    	}
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi)
    {
        if (lo == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (hi == null)
    	{
    		throw new IllegalArgumentException();
    	}

        Queue<K> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<K> queue, K lo, K hi)
    {
        if (node == null)
    	{
    		return;
    	}
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0)
    	{
    		keys(node.left, queue, lo, hi);
    	}
        if (cmplo <= 0 && cmphi >= 0)
    	{
    		queue.enqueue(node.key);
    	}
        if (cmphi > 0)
    	{
    		keys(node.right, queue, lo, hi);
    	}
    }

    public int size(K lo, K hi)
    {
        if (lo == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (hi == null)
    	{
    		throw new IllegalArgumentException();
    	}

        if (lo.compareTo(hi) > 0)
    	{
    		return 0;
    	}
        if (contains(hi))
    	{
    		return rank(hi) - rank(lo) + 1;
    	}
        else   
    	{
    		return rank(hi) - rank(lo);
    	}
    }

    public int height()
    {
        return height(root);
    }
    
    private int height(Node node)
    {
        if (node == null)
    	{
    		return -1;
    	}
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    public int heightRecursive() 
    {
        return heightRecursive(root);
    }

    private int heightRecursive(Node node)
    {
        if (node == null)
        {
            return -1;
        }

        return Math.max(heightRecursive(node.left), heightRecursive(node.right)) + 1;
    }
    
    public int heightConstant()
    {
        return heightConstant(root);
    }

    private int heightConstant(Node node)
    {
        if (node == null)
        {
            return -1;
        }

        return node.height;
    }
}
