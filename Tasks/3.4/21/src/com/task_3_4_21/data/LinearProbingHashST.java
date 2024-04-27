package com.task_3_4_21.data;

import com.task_1_3_37.datastructures.Queue;

public class LinearProbingHashST<K, V> 
{
    protected int size;
    protected int capacity;
    protected K[] keys;
    protected V[] values;
    private static final int[] PRIMES = {
            1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
            32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
            8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
            536870909, 1073741789, 2147483647
    };
    private int lgM;

    @SuppressWarnings("unchecked")
	public LinearProbingHashST(int capacity) 
    {
        this.capacity = capacity;
        size = 0;
        keys = (K[])   new Object[capacity];
        values = (V[]) new Object[capacity];
        
        lgM = (int) (Math.log(capacity) / Math.log(2));
    }

    public int size() 
    {
        return size;
    }

    public boolean isEmpty() 
    {
        return size() == 0;
    }

    public boolean contains(K key) 
    {
        if (key == null) 
    	{
    		throw new IllegalArgumentException();
    	}
        return get(key) != null;
    }

    private int hash(K key) {
        int hash = key.hashCode() & 0x7fffffff;

        if (lgM < 26) {
            hash = hash % PRIMES[lgM + 5];
        }

        return hash % capacity;
    }

    protected double getLoadFactor() 
    {
        return size / (double) capacity;
    }
    
    private void resize(int capacity) 
    {
        LinearProbingHashST<K, V> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < capacity; ++i) 
        {
            if (keys[i] != null) 
            {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        this.capacity = temp.capacity;
    }

    public void put(K key, V val)
    {
        if (key == null) 
    	{
    		throw new IllegalArgumentException();
    	}
        if (val == null) 
        {
            delete(key);
            return;
        }

        if (size >= capacity / 2) 
    	{
    		resize(2 * capacity);
    	}

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) 
        {
            if (keys[i].equals(key))
            {
                values[i] = val;
                return;
            }
        }
        keys[i] = key;
        values[i] = val;
        ++size;
    }

    public V get(K key) 
    {
        if (key == null) 
    	{
    		throw new IllegalArgumentException();
    	}
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity)
        {
            if (keys[i].equals(key))
            {
        		return values[i];
            }
        }
        return null;
    }

    public void delete(K key)
    {
        if (key == null)
    	{
    		throw new IllegalArgumentException();
    	}
        if (!contains(key)) 
    	{
    		return;
    	}

        int i = hash(key);
        while (!key.equals(keys[i]))
        {
            i = (i + 1) % capacity;
        }

        keys[i] = null;
        values[i] = null;

        i = (i + 1) % capacity;
        while (keys[i] != null) 
        {
            K keyToRehash = keys[i];
            V valToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            --size;
            put(keyToRehash, valToRehash);
            i = (i + 1) % capacity;
        }
        --size;

        if (size > 0 && size <= capacity / 8)
    	{
    		resize(capacity / 2);
    	}
    }

    public Iterable<K> keys()
    {
        Queue<K> queue = new Queue<>();
        for (int i = 0; i < capacity; ++i)
        {
            if (keys[i] != null) 
        	{
        		queue.enqueue(keys[i]);
        	}
        }
        return queue;
    }
}