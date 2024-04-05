package com.task_1_3_14;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueueOfStrings
{
	private String[] q = (String[]) new Object[1];
	private int n = 0;
	
	public boolean isEmpty()
	{
		return n == 0;
	}
	
	public int size()
	{
		return n;
	}
	
	private void resize(int x)
	{
		String[] temp = (String[]) new Object[x];
		for (int i = 0; 0 < n; ++i)
		{
			temp[i] = q[i];
		}
		q = temp;
	}
	
	public void enqueue(String str)
	{
		if (n == q.length)
		{
			resize(2 * q.length);
		}
		q[n++] = str;
	}
	
	public String dequeue()
	{
		String str = q[0];
		q[0] = null;
		if (n > 0 && n == q.length / 4)
		{
			resize(q.length / 2);
		}
		return str;
	}
	
	public Iterator<String> iterator()
	{
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<String>
	{
		private int i = 1;
		
		public boolean hasNext()
		{
			return i < n;
		}
		
		public String next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			return q[i++];
		}
	}
}
