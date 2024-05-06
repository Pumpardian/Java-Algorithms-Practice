package com.task_5_2_xx.data;

import java.util.*;

public class StringSet 
{
    protected class Node 
    {
        private Map<Character, Node> next = new HashMap<>();
        protected boolean isKey;
        
		public Map<Character, Node> getNext()
		{
			return next;
		}
		
		public void setNext(Map<Character, Node> next)
		{
			this.next = next;
		}
    }

    protected Node root = new Node();
    private int size;

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public boolean contains(String key)
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        return contains(root, key, 0);
    }

    private boolean contains(Node node, String key, int digit) 
    {
        if (node == null) 
        {
            return false;
        }
        if (digit == key.length()) 
        {
            return node.isKey;
        }

        char nextChar = key.charAt(digit);
        return contains(node.getNext().get(nextChar), key, digit + 1);
    }

    public void add(String key)
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        if (contains(key)) 
        {
            return;
        }

        root = add(root, key, 0);
        ++size;
    }

    private Node add(Node node, String key, int digit)
    {
        if (node == null)
        {
            node = new Node();
        }
        if (digit == key.length())
        {
            node.isKey = true;
            return node;
        }

        char nextChar = key.charAt(digit);
        Node nextNode = add(node.getNext().get(nextChar), key, digit + 1);
        node.getNext().put(nextChar, nextNode);
        return node;
    }

    public void delete(String key)
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        if (!contains(key))
        {
            return;
        }

        root = delete(root, key, 0);
        --size;
    }

    private Node delete(Node node, String key, int digit) 
    {

        if (digit == key.length())
        {
            node.isKey = false;
        } 
        else
        {
            char nextChar = key.charAt(digit);
            Node childNode = delete(node.getNext().get(nextChar), key, digit + 1);
            if (childNode != null) 
            {
                node.getNext().put(nextChar, childNode);
            }
            else 
            {
                node.getNext().remove(nextChar);
            }
        }

        if (node.isKey || node.getNext().size() > 0) 
        {
            return node;
        }

        return null;
    }

    public Iterable<String> keys()
    {
        List<String> keys = new ArrayList<>();
        keys(root, new StringBuilder(), keys);
        Collections.sort(keys);
        return keys;
    }

    private void keys(Node node, StringBuilder prefix, List<String> keys) 
    {
        if (node == null)
        {
            return;
        }
        if (node.isKey) 
        {
            keys.add(prefix.toString());
        }
        for (Character character : node.getNext().keySet())
        {
            keys(node.getNext().get(character), new StringBuilder(prefix).append(character), keys);
        }
    }

    @Override
    public String toString()
    {
        StringJoiner keys = new StringJoiner(", ");
        for (String key : keys())
        {
            keys.add(key);
        }
        return "{ " + keys.toString() + " }";
    }
}
