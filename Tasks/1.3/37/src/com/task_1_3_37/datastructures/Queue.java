package com.task_1_3_37.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.task_x_x_xx.dependencies.Node;

public class Queue<T> implements Iterable<T>
{
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public int size()
	{
		return size;
	}
	
	public void enqueue(T item)
	{
        if (item == null) 
        {
            throw new IllegalArgumentException();
        }
		Node<T> oldLast = tail;
		tail = new Node<>(item);
		tail.setNext(null);
		if (isEmpty())
		{
			head = tail;
		}
		else
		{
			oldLast.setNext(tail);
		}
		++size;
	}
	
	public T dequeue()
	{
        if (isEmpty()) 
        {
            throw new NoSuchElementException();
        }
		T item = head.getItem();
		head = head.next();
		--size;
		if (isEmpty())
		{
			tail = null;
		}
		return item;
	}
	
    public T peek()
    {
        if (isEmpty()) 
        {
            throw new NoSuchElementException();
        }

        return head.getItem();
    }
	
	public Iterator<T> iterator()
	{
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<T>
	{
		private Node<T> current = head;
		
		public boolean hasNext()
		{
			return current != null;
		}
		
		public T next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			T item = current.getItem();
			current = current.next();
			return item;
		}
	}
}
