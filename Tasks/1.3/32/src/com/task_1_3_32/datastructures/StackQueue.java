package com.task_1_3_32.datastructures;

import com.task_1_3_xx.dependencies.Node;

public class StackQueue<T>
{
	private Node<T> first;
	private Node<T> last;
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public void push(T item)
	{
		Node<T> node = new Node<>(item);
		if (isEmpty())
		{
			first = node;
			last = node;
		}
		else
		{
			node.setNext(first);
			first = node;
		}
	}
	
	public T pop()
	{
		if (isEmpty())
		{
			return null;
		}
		T item = first.getItem();
		first = first.next();
		if (first == null)
		{
			last = null;
		}
		return item;
	}
	
	public void append(T item)
	{
		Node<T> node = new Node<>(item);
		if (isEmpty())
		{
			first = node;
		}
		else
		{
			last.setNext(node);
		}
		last = node;
	}
	
	public T peek()
	{
		if (isEmpty())
		{
			return null;
		}
		return first.getItem();
	}
}
