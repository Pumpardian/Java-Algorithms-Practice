package com.task_1_3_37.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.task_1_3_xx.dependencies.Node;

public class Queue<T>
{
	private Node<T> first;
	private Node<T> last;
	private int n;
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public int size()
	{
		return n;
	}
	
	public void enqueue(T item)
	{
		Node<T> oldLast = last;
		last = new Node<>(item);
		last.setNext(null);
		if (isEmpty())
		{
			first = null;
		}
		else
		{
			oldLast.setNext(last);
		}
		++n;
	}
	
	public T dequeue()
	{
		T item = first.getItem();
		first = first.next();
		if (isEmpty())
		{
			last = null;
		}
		--n;
		return item;
	}
	
	public Iterator<T> iterator()
	{
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<T>
	{
		private Node<T> current = first;
		
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
