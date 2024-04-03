import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>
{
	private Node<Item> first;
	private int N;
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public int size()
	{
		return N;
	}
	
	public void push(Item item)
	{
		Node<Item> _first = first;
		first = new Node<Item>(item);
		first.setNext(_first);
		++N;
	}
	
	public Item pop()
	{
		Item item = first.getItem();
		first = first.next();
		--N;
		return item;
	}
	
	public static Stack<String> copy(Stack<String> stack)
	{
		Stack<String> copy = new Stack<String>();
		for (String s : stack)
		{
			copy.push(s);
		}
		return copy;
	}
	
	public Iterator<Item> iterator()
	{
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>
	{
		private Node<Item> current = first;
		
		public boolean hasNext()
		{
			return current != null;
		}
		
		public Item next()
		{
			Item item = current.getItem();
			current = current.next();
			return item;
		}
		
		public void remove() {}
	}
}
