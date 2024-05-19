package com.task_5_4_xx;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<I> implements Iterable<I> 
{
    private Node<I> first;
    private int size;

    private static class Node<I>
    {
        private I item;
        private Node<I> next;
    }

    public Bag() 
    {
        first = null;
        size = 0;
    }

    public boolean isEmpty() 
    {
        return first == null;
    }

    public int size()
    {
        return size;
    }

    public void add(I item)
    {
        Node<I> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        ++size;
    }

    public Iterator<I> iterator() 
    {
        return new BagIterator(first);
    }

    private class BagIterator implements Iterator<I>
    {
        private Node<I> current;
        
        public BagIterator(Node<I> first)
        {
            current = first;
        }

        public boolean hasNext()
        {
            return current != null;
        }

        public I next() 
        {
            if (!hasNext())
        	{
        		throw new NoSuchElementException();
        	}
            I item = current.item;
            current = current.next;
            return item;
        }
    }
}