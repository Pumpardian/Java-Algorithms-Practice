package com.task_2_5_24.datastructures;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class StableMinPQ<K extends Comparable<K>>
{
	private K[] pq;
	private long[] time;
	private int size;
	private long timeStamp = 1;
	
	public StableMinPQ(int capacity)
	{
		pq = (K[]) new Comparable[capacity + 1];
		time = new long[capacity + 1];
		size = 0;
	}
	
	public StableMinPQ()
	{
		this(1);
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public int size()
	{
		return size;
	}
	
	public K min()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		return pq[1];
	}
	
	private void resize(int capacity)
	{
		assert capacity > size;
		K[] tempPQ = (K[]) new Comparable[capacity];
		long[] tempTime = new long[capacity];
		for (int i = 1; i <= size; ++i)
		{
			tempPQ[i] = pq[i];
			tempTime[i] = time[i];
		}
		pq = tempPQ;
		time = tempTime;
	}
	
	public void insert(K x)
	{
		if (size == pq.length - 1)
		{
			resize(2 * pq.length);
		}
		pq[++size] = x;
		time[size] = ++timeStamp;
		swim(size);
		assert isMinHeap();
	}
	
	public K delMin()
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}
		swap(1, size);
		K min = pq[size--];
		sink(1);
		pq[size + 1] = null;
		time[size + 1] = 0;
		if ((size > 0) && (size == (pq.length - 1) / 4))
		{
			resize(pq.length / 2);
		}
		return min;
	}
	
	private void swim(int x)
	{
		while (x > 1 && greater(x / 2, x))
		{
			swap(x, x / 2);
			x /= 2;
		}
	}
	
	private void sink(int x)
	{
		while (2 * x <= size)
		{
			int j = 2 * x;
			if (j < size && greater(j, j + 1))
			{
				++j;
			}
			if (!greater(x, j))
			{
				break;
			}
			swap(x, j);
			x = j;
		}
	}
	
	private boolean greater(int i, int j)
	{
		int cmp = pq[i].compareTo(pq[j]);
		if (cmp > 0)
		{
			return true;
		}
		if (cmp < 0)
		{
			return false;
		}
		return time[i] > time[j];
	}
	
	private void swap(int i, int j)
	{
		K temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		long tempTime = time[i];
		time[i] = time[j];
		time[j] = tempTime;
	}
	
	private boolean isMinHeap()
	{
		return isMinHeap(1)
;	}
	
	private boolean isMinHeap(int x)
	{
		if (x > size)
		{
			return true;
		}
		int left = 2 * x;
		int right = 2 * x + 1;
		if (left <= size && greater(x, left))
		{
			return false;
		}
		if (left <= size && greater(x, right))
		{
			return false;
		}
		return isMinHeap(left) && isMinHeap(right);
	}
}
