package com.task.datastructures;

public class StackQueue<Item>
{
	private Node<Item> first;
	private Node<Item> last;
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public void push(Item item)
	{
		Node<Item> node = new Node<>(item);
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
	
	public Item pop()
	{
		if (isEmpty())
		{
			return null;
		}
		Item item = first.getItem();
		first = first.next();
		if (first == null)
		{
			last = null;
		}
		return item;
	}
	
	public void append(Item item)
	{
		Node<Item> node = new Node<>(item);
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
	
	public Item peek()
	{
		if (isEmpty())
		{
			return null;
		}
		return first.getItem();
	}
}
