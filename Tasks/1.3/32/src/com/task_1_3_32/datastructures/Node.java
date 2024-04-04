package com.task_1_3_32.datastructures;

public class Node<T>
{
	private final T item;
	private Node<T> next;
	
	public Node(T item)
	{
		this.item = item;
	}
	
	public T getItem()
	{
		return item;
	}
	
	public Node<T> next()
	{
		return next;
	}
	
	public void setNext(Node<T> next)
	{
		this.next = next;
	}
}
