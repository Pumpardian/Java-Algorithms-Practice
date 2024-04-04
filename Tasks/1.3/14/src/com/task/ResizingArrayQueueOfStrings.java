package com.task;

import java.util.Iterator;

public class ResizingArrayQueueOfStrings
{
	private String[] q = (String[]) new Object[1];
	private int N = 0;
	
	public boolean isEmpty()
	{
		return N == 0;
	}
	
	public int size()
	{
		return N;
	}
	
	private void resize(int n)
	{
		String[] temp = (String[]) new Object[n];
		for (int i = 0; 0 < N; ++i)
		{
			temp[i] = q[i];
		}
		q = temp;
	}
	
	public void enqueue(String str)
	{
		if (N == q.length)
		{
			resize(2 * q.length);
		}
		q[N++] = str;
	}
	
	public String dequeue()
	{
		String str = q[0];
		q[0] = null;
		if (N > 0 && N == q.length / 4)
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
			return i < N;
		}
		
		public String next()
		{
			return q[i++];
		}
		
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}
	}
}
