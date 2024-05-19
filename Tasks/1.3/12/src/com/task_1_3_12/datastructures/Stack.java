package com.task_1_3_12.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.task_x_x_xx.dependencies.Node;

public class Stack<T> implements Iterable<T>
{
	private Node<T> top;
	private int size;
	
	public boolean isEmpty()
	{
		return top == null;
	}
	
	public int size()
	{
		return size;
	}
	
	public void push(T item)
	{
		Node<T> oldFirst = top;
		top = new Node<>(item);
		top.setNext(oldFirst);
		++size;
	}
	
	public T pop()
	{
		T item = top.getItem();
		top = top.next();
		--size;
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
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<T>
	{
		private Node<T> current = top;
		
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

    public T peek() 
    {
        if (isEmpty()) 
        {
            return null;
        }

        return top.getItem();
    }
}
