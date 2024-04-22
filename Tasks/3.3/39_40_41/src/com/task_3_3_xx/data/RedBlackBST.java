package com.task_3_3_xx.data;

import java.util.NoSuchElementException;
import com.task_1_3_37.datastructures.Queue;

public class RedBlackBST<K extends Comparable<K>, V>
{
    public static final boolean RED   = true;
    public static final boolean BLACK = false;	
    private Node root;

    public class Node
    {
    	private K key;
    	private V value;
    	private Node left;
    	private Node right;
    	private boolean color;
    	private int size;

        public Node(K key, V value, boolean color, int size)
        {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }

		public Node getLeft()
		{
			return left;
		}

		public void setLeft(Node left)
		{
			this.left = left;
		}

		public Node getRight()
		{
			return right;
		}

		public void setRight(Node right)
		{
			this.right = right;
		}

		public K getKey()
		{
			return key;
		}

		public void setKey(K key) 
		{
			this.key = key;
		}

		public boolean isColor()
		{
			return color;
		}

		public void setColor(boolean color)
		{
			this.color = color;
		}
    }
    
    public int size()
    {
        return size(getRoot());
    }

    private int size(Node node)
    {
        if (node == null)
    	{
    		return 0;
    	}
        else
    	{
    		return node.size;
    	}
    }

    protected boolean isRed(Node node)
    {
    	if (node == null)
    	{
    		return false;
    	}
    	return node.isColor() == RED;
    }
    
    public boolean isEmpty()
    {
        return getRoot() == null;
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
        return get(getRoot(), key);
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
        int cmp = key.compareTo(node.getKey());
        if (cmp < 0)
    	{
    		return get(node.getLeft(), key);
    	}
        else if (cmp > 0)
    	{
    		return get(node.getRight(), key);
    	}
        else  
    	{
    		return node.value;
    	}
    }

    public void put(K key, V val) 
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (val == null)
        {
            delete(key);
            return;
        }
        setRoot(put(getRoot(), key, val));
        getRoot().setColor(BLACK);
    }

    private Node put(Node node, K key, V value)
    {
        if (node == null)
    	{
    		return new Node(key, value, RED, 1);
    	}
        int cmp = key.compareTo(node.getKey());
        if (cmp < 0) 
    	{
        	node.setLeft(put(node.getLeft(), key, value));
    	}
        else if (cmp > 0) 
    	{
        	node.setRight(put(node.getRight(), key, value));
    	}
        else             
    	{
        	node.value   = value;
    	}
        if (isRed(node.getRight()) && !isRed(node.getLeft()))  
    	{
        	node = rotateLeft(node);
    	}
        if (isRed(node.getLeft())  &&  isRed(node.getLeft().getLeft()))
    	{
        	node = rotateRight(node);
    	}
        if (isRed(node.getLeft())  &&  isRed(node.getRight()))   
    	{
    		flipColors(node);
    	}
        node.size = 1 + size(node.getLeft()) + size(node.getRight());
        return node;
    }

    public void deleteMin()
    {
        if (isEmpty()) 
    	{
    		throw new NoSuchElementException();
    	}
        if (!isRed(getRoot().getLeft()) && !isRed(getRoot().getRight()))
        {
    		getRoot().setColor(RED);
        }
        setRoot(deleteMin(getRoot()));
        if (!isEmpty()) 
    	{
    		getRoot().setColor(BLACK);
    	}
    }

    private Node deleteMin(Node node)
    {
        if (node.getLeft() == null)
    	{
    		return node.getRight();
    	}
        if (!isRed(node.getLeft()) && !isRed(node.getLeft().getLeft()))
        {
        	node = moveRedLeft(node);
        }
        node.setLeft(deleteMin(node.getLeft()));
        return balance(node);
    }
    
    public void deleteMax()
    {
        if (isEmpty())
    	{
        	throw new NoSuchElementException();
    	}
        if (!isRed(getRoot().getLeft()) && !isRed(getRoot().getRight()))
    	{
    		getRoot().setColor(RED);
    	}
        setRoot(deleteMax(getRoot()));
        if (!isEmpty()) 
    	{
    		getRoot().setColor(BLACK);
    	}
    }

    private Node deleteMax(Node node)
    {
        if (isRed(node.getLeft()))   
    	{
    		node = rotateRight(node);
    	}
        
        if (node.getRight() == null)
    	{
    		return null;
    	}
        
        if (!isRed(node.getRight()) && !isRed(node.getRight().getLeft()))
        {
        	node = moveRedRight(node);
        }
        node.setRight(deleteMax(node.getRight()));
        return balance(node);
    }

    public void delete(K key)
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (!isRed(getRoot().getLeft()) && !isRed(getRoot().getRight()))
        {
    		getRoot().setColor(RED);
        }

        setRoot(delete(getRoot(), key));
        if (!isEmpty())
    	{
    		getRoot().setColor(BLACK);
    	}
    }

    private Node delete(Node node, K key)
    {
        if (node == null)
    	{
    		return null;
    	}

        int cmp = key.compareTo(node.getKey());
        if (cmp < 0)
    	{
            if (!isRed(node.getLeft()) && !isRed(node.getLeft().getLeft()))
            {
        		node = moveRedLeft(node);
            }
            node.setLeft(delete(node.getLeft(), key));
    	}
        else if (isRed(node.getLeft()))
        {
    		node = rotateRight(node);
        }
        else if (key.compareTo(node.getKey()) == 0 && (node.getRight() == null))
        {
    		return null;
        }
        else if (!isRed(node.getRight()) && !isRed(node.getRight().getLeft()))
        {
    		node = moveRedRight(node);
        }
        else if (key.compareTo(node.getKey()) == 0)
        {
            Node t = min(node.getRight());
            node.setKey(t.getKey());
            node.value = t.value;
            node.setRight(deleteMin(node.getRight()));
        }
        else
    	{
    		node.setRight(delete(node.getRight(), key));
    	}
        
        return balance(node);
    }
    
    private Node rotateRight(Node h)
    {
        assert (h != null) && isRed(h.getLeft());
        Node node = h.getLeft();
        h.setLeft(node.getRight());
        node.setRight(h);
        node.setColor(h.isColor());
        h.setColor(RED);
        node.size = h.size;
        h.size = size(h.getLeft()) + size(h.getRight()) + 1;
        return node;
    }

    private Node rotateLeft(Node h) 
    {
        assert (h != null) && isRed(h.getRight());
        Node node = h.getRight();
        h.setRight(node.getLeft());
        node.setLeft(h);
        node.setColor(h.isColor());
        h.setColor(RED);
        node.size = h.size;
        h.size = size(h.getLeft()) + size(h.getRight()) + 1;
        return node;
    }

    private void flipColors(Node h)
    {
        h.setColor(!h.isColor());
        h.getLeft().setColor(!h.getLeft().isColor());
        h.getRight().setColor(!h.getRight().isColor());
    }

    private Node moveRedLeft(Node h)
    {
        flipColors(h);
        if (isRed(h.getRight().getLeft()))
        {
            h.setRight(rotateRight(h.getRight()));
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h)
    {
        flipColors(h);
        if (isRed(h.getLeft().getLeft()))
        {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h)
    {
        if (isRed(h.getRight()) && !isRed(h.getLeft()))
    	{
    		h = rotateLeft(h);
    	}
        if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft()))
    	{
    		h = rotateRight(h);
    	}
        if (isRed(h.getLeft()) && isRed(h.getRight()))    
    	{
    		flipColors(h);
    	}

        h.size = size(h.getLeft()) + size(h.getRight()) + 1;
        return h;
    }

    public K min()
    {
        if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
        return min(getRoot()).getKey();
    }

    private Node min(Node node)
    {
        if (node.getLeft() == null)
    	{
    		return node;
    	}
        else
    	{
    		return min(node.getLeft());
    	}
    }

    public K max()
    {
        if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
        return max(getRoot()).getKey();
    }

    private Node max(Node node)
    {
        if (node.getRight() == null)
    	{
    		return node;
    	}
        else 
    	{
    		return max(node.getRight());
    	}
    }

    public K floor(K key)
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
        Node node = floor(getRoot(), key);
        if (node == null)
    	{
    		throw new NoSuchElementException();
    	}
        else return node.getKey();
    }

    private Node floor(Node node, K key)
    {
        if (node == null)
    	{
    		return null;
    	}
        int cmp = key.compareTo(node.getKey());
        if (cmp == 0)
    	{
    		return node;
    	}
        if (cmp <  0) 
    	{
    		return floor(node.getLeft(), key);
    	}
        Node t = floor(node.getRight(), key);
        if (t != null)
    	{
    		return t;
    	}
        else return node;
    }

    public K ceiling(K key)
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (isEmpty())
    	{
    		throw new NoSuchElementException();
    	}
        Node node = ceiling(getRoot(), key);
        if (node == null)
    	{
    		throw new NoSuchElementException();
    	}
        else return node.getKey();
    }

    private Node ceiling(Node node, K key)
    {
        if (node == null)
    	{
    		return null;
    	}
        int cmp = key.compareTo(node.getKey());
        if (cmp == 0)
    	{
    		return node;
    	}
        if (cmp < 0) 
        {
            Node t = ceiling(node.getLeft(), key);
            if (t != null)
        	{
        		return t;
        	}
            else
        	{
        		return node;
        	}
        }
        return ceiling(node.getRight(), key);
    }
    
    private void keys(Node node, Queue<K> queue, K lo, K hi)
    {
        if (node == null)
    	{
    		return;
    	}
        int cmplo = lo.compareTo(node.getKey());
        int cmphi = hi.compareTo(node.getKey());
        if (cmplo < 0)
    	{
    		keys(node.getLeft(), queue, lo, hi);
    	}
        if (cmplo <= 0 && cmphi >= 0)
    	{
    		queue.enqueue(node.getKey());
    	}
        if (cmphi > 0)
    	{
    		keys(node.getRight(), queue, lo, hi);
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
        keys(getRoot(), queue, lo, hi);
        return queue;
    }
    
    public K select(int rank)
    {
        if (rank < 0 || rank >= size())
        {
            throw new IllegalArgumentException();
        }
        return select(getRoot(), rank);
    }

    private K select(Node node, int rank)
    {
        if (node == null)
    	{
    		return null;
    	}
        int leftSize = size(node.getLeft());
        if (leftSize > rank)
    	{
    		return select(node.getLeft(),  rank);
    	}
        else if (leftSize < rank)
    	{
    		return select(node.getRight(), rank - leftSize - 1);
    	}
        else 
    	{
    		return node.getKey();
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

    public int rank(K key)
    {
        if (key == null)
        {
        	throw new IllegalArgumentException();
        }
        return rank(key, getRoot());
    }

    private int rank(K key, Node node)
    {
        if (node == null)
    	{
    		return 0;
    	}
        int cmp = key.compareTo(node.getKey());
        if (cmp < 0)
        {
        	return rank(key, node.getLeft());
		}
        else if (cmp > 0) 
    	{
    		return 1 + size(node.getLeft()) + rank(key, node.getRight());
    	}
        else
    	{
    		return size(node.getLeft());
    	}
    }

    public int height()
    {
        return height(getRoot());
    }
    
    private int height(Node node)
    {
        if (node == null)
    	{
    		return -1;
    	}
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

	public Node getRoot()
	{
		return root;
	}

	public void setRoot(Node root)
	{
		this.root = root;
	}
    
    
}

