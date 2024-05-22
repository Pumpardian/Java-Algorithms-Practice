package com.task_5_4_xx;

import com.task_1_3_37.datastructures.Queue;

@SuppressWarnings("unchecked")
public class SeparateChainingHashTable<K, V>
{
    public class SequentialSearchSymbolTable<X, Y>
    {

        private class Node
        {
            X key;
            Y value;
            Node next;

            public Node(X key, Y value, Node next)
            {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        private Node first;
        protected int size;

        public int size() 
        {
            return size;
        }

        public boolean isEmpty() 
        {
            return size == 0;
        }

        public boolean contains(X key)
        {
            return get(key) != null;
        }

        public Y get(X key)
        {
            for (Node node = first; node != null; node = node.next)
            {
                if (key.equals(node.key))
                {
                    return node.value;
                }
            }
            return null;
        }

        public void put(X key, Y value) 
        {
            for (Node node = first; node != null; node = node.next) 
            {
                if (key.equals(node.key))
                {
                    node.value = value;
                    return;
                }
            }
            first = new Node(key, value, first);
            ++size;
        }

        public void delete(X key) 
        {
            if (first.key.equals(key))
            {
                first = first.next;
                --size;
                return;
            }

            for (Node node = first; node != null; node = node.next)
            {
                if (node.next != null && node.next.key.equals(key))
                {
                    node.next = node.next.next;
                    --size;
                    return;
                }
            }
        }

        public Iterable<X> keys()
        {
            Queue<X> keys = new Queue<>();

            for (Node node = first; node != null; node = node.next)
            {
                keys.enqueue(node.key);
            }
            return keys;
        }

    }

    protected int averageListSize;
    protected int size;
    protected int keysSize;
    SequentialSearchSymbolTable<K, V>[] symbolTable;

    private static final int DEFAULT_HASH_TABLE_SIZE = 997;
    private static final int DEFAULT_AVERAGE_LIST_SIZE = 5;
    protected static final int[] PRIMES = {
            1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
            32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
            8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
            536870909, 1073741789, 2147483647
    };
    
    protected int lgM;

    public SeparateChainingHashTable() 
    {
        this(DEFAULT_HASH_TABLE_SIZE, DEFAULT_AVERAGE_LIST_SIZE);
    }

    public SeparateChainingHashTable(int initialSize, int averageListSize)
    {
        this.size = initialSize;
        this.averageListSize = averageListSize;
        symbolTable = new SequentialSearchSymbolTable[size];

        for (int i = 0; i < size; ++i) 
        {
            symbolTable[i] = new SequentialSearchSymbolTable<>();
        }
        lgM = (int) (Math.log(size) / Math.log(2));
    }

    public boolean isEmpty() 
    {
        return keysSize == 0;
    }

    protected int hash(K key) 
    {
        int hash = key.hashCode() & 0x7fffffff;

        if (lgM < 26)
        {
            hash = hash % PRIMES[lgM + 5];
        }
        return hash % size;
    }

    protected double getLoadFactor() 
    {
        return ((double) keysSize) / (double) size;
    }

    public int size() 
    {
        return keysSize;
    }

    public boolean contains(K key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    public void resize(int newSize) 
    {
        SeparateChainingHashTable<K, V> separateChainingHashTableTemp = new SeparateChainingHashTable<>(newSize, averageListSize);

        for (K key : keys()) 
        {
            separateChainingHashTableTemp.put(key, get(key));
        }
        symbolTable = separateChainingHashTableTemp.symbolTable;
        size = separateChainingHashTableTemp.size;
    }

    public V get(K key) 
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return symbolTable[hash(key)].get(key);
    }

    public void put(K key, V value)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        if (value == null)
        {
            delete(key);
            return;
        }

        int hashIndex = hash(key);
        int currentSize = symbolTable[hashIndex].size;
        symbolTable[hashIndex].put(key, value);
        if (currentSize < symbolTable[hashIndex].size)
        {
            ++keysSize;
        }
        if (getLoadFactor() > averageListSize) {
            resize(size * 2);
            lgM++;
        }
    }

    public void delete(K key)
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        if (isEmpty() || !contains(key)) 
        {
            return;
        }

        symbolTable[hash(key)].delete(key);
        --keysSize;
        if (size > 1 && getLoadFactor() <= averageListSize / (double) 4)
        {
            resize(size / 2);
            --lgM;
        }
    }

    public Iterable<K> keys()
    {
        Queue<K> keys = new Queue<>();

        for (SequentialSearchSymbolTable<K, V> sequentialSearchST : symbolTable) 
        {
            for (K key : sequentialSearchST.keys()) 
            {
                keys.enqueue(key);
            }
        }
        return keys;
    }
}