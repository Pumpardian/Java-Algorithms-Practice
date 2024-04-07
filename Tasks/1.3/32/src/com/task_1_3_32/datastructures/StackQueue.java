package com.task_1_3_32.datastructures;

import com.task_x_x_xx.dependencies.Node;

public class StackQueue<T>
{
	private Node<T> head;
	private Node<T> tail;
	
	public boolean isEmpty()
	{
		return head == null;
	}
	
	public void push(T item)
	{
		Node<T> node = new Node<>(item);
		if (isEmpty())
		{
			head = node;
			tail = node;
		}
		else
		{
			node.setNext(head);
			head = node;
		}
	}
	
	public T pop()
	{
		if (isEmpty())
		{
			return null;
		}
		T item = head.getItem();
		head = head.next();
		if (head == null)
		{
			tail = null;
		}
		return item;
	}
	
	public void append(T item)
	{
		Node<T> node = new Node<>(item);
		if (isEmpty())
		{
			head = node;
		}
		else
		{
			tail.setNext(node);
		}
		tail = node;
	}
	
	public T peek()
	{
		if (isEmpty())
		{
			return null;
		}
		return head.getItem();
	}
}
