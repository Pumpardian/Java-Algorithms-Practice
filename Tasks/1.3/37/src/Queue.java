import java.util.Iterator;

public class Queue<Item>
{
	private Node<Item> first;
	private Node<Item> last;
	private int N;
	
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public int size()
	{
		return N;
	}
	
	public void enqueue(Item item)
	{
		Node<Item> _last = last;
		last = new Node<>(item);
		last.setNext(null);
		if (isEmpty())
		{
			first = null;
		}
		else
		{
			_last.setNext(last);
		}
		++N;
	}
	
	public Item dequeue()
	{
		Item item = first.getItem();
		first = first.next();
		if (isEmpty())
		{
			last = null;
		}
		--N;
		return item;
	}
	
	public Iterator<Item> iterator()
	{
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item>
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
