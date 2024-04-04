package com.task.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T>
{
	private Node<T> first;
	private int n;
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public int size()
	{
		return n;
	}
	
	public void push(T item)
	{
		Node<T> oldFirst = first;
		first = new Node<>(item);
		first.setNext(oldFirst);
		++n;
	}
	
	public T pop()
	{
		T item = first.getItem();
		first = first.next();
		--n;
		return item;
	}
	
	public static Stack<String> copy(Stack<String> stack)
	{
		Stack<String> copy = new Stack<>();
		for (String s : stack)
		{
			copy.push(s);
		}
		return copy;
	}
	
	public Iterator<T> iterator()
	{
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<T>
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
