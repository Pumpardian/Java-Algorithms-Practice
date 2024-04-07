package com.task_1_3_14.datastructures;

import java.util.NoSuchElementException;

public class ResizingArrayQueueOfStrings
{
	private String[] queue;
	private int size = 0;
	private int head = 0;
	private int tail = 0;
	
	public ResizingArrayQueueOfStrings(int capacity)
	{
		queue = new String[capacity];
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public int size()
	{
		return size;
	}
	
	private void resize(int capacity)
	{
		String[] temp = new String[capacity];
		for (int i = 0; i < size; ++i)
		{
            temp[i] = queue[(head + i) % queue.length];
		}
		queue = temp;
		head = 0;
		tail = size;
	}
	
	public void enqueue(String str)
	{
		if (size == queue.length)
		{
			resize(2 * queue.length);
		}
		
		
        if (tail >= queue.length)
        {
            tail = 0;
        }
        
    	queue[tail++] = str;
    	++size;
	}
	
    public String dequeue() 
    {
        if (isEmpty()) 
        {
            throw new NoSuchElementException();
        }
        else 
        {
            String item = queue[head];
            queue[head++] = null;

            if (head == queue.length)
            {
                head = 0;
            }
            --size;

            if (size > 0 && size == queue.length / 4)
            {
                resize(queue.length / 2);
            }

            return item;
        }
    }
}
