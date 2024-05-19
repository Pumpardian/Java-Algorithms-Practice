package com.task_5_4_xx;

import com.task_1_3_37.datastructures.Queue;

@SuppressWarnings("unchecked")
public class HashSet<T>
{

    private class SeparateChainingHashTable<K>
    {

        private class SequentialSearchSymbolTable<H>
        {
            private class Node 
            {
                H key;
                Node next;

                public Node(H key, Node next) 
                {
                    this.key = key;
                    this.next = next;
                }
            }

            private Node first;
            private int size;

            public int size() 
            {
                return size;
            }

            public boolean isEmpty() 
            {
                return size == 0;
            }

            public boolean contains(H key) 
            {
                for (Node node = first; node != null; node = node.next) 
                {
                    if (key.equals(node.key)) 
                    {
                        return true;
                    }
                }
                return false;
            }

            public void put(H key)
            {
                for (Node node = first; node != null; node = node.next) 
                {
                    if (key.equals(node.key)) 
                    {
                        node.key = key;
                        return;
                    }
                }
                first = new Node(key, first);
                ++size;
            }

            public void delete(H key) 
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

            public Iterable<H> keys() 
            {
                Queue<H> keys = new Queue<>();

                for (Node node = first; node != null; node = node.next)
                {
                    keys.enqueue(node.key);
                }
                return keys;
            }
        }

        private int averageListSize;
        private int size;
        private int keysSize;
        private SequentialSearchSymbolTable<K>[] symbolTable;

        private static final int DEFAULT_HASH_TABLE_SIZE = 997;
        private static final int DEFAULT_AVERAGE_LIST_SIZE = 5;
        private static final int[] PRIMES = {
                1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
                32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
                8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
                536870909, 1073741789, 2147483647
        };
        
        private int lgM;

        private SeparateChainingHashTable() 
        {
            this(DEFAULT_HASH_TABLE_SIZE, DEFAULT_AVERAGE_LIST_SIZE);
        }

        private SeparateChainingHashTable(int initialSize, int averageListSize)
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

        public int size() 
        {
            return keysSize;
        }

        public boolean isEmpty() 
        {
            return keysSize == 0;
        }

        private int hash(K key)
        {
            int hash = key.hashCode() & 0x7fffffff;

            if (lgM < 26)
            {
                hash = hash % PRIMES[lgM + 5];
            }
            return hash % size;
        }

        private double getLoadFactor() 
        {
            return ((double) keysSize) / (double) size;
        }

        public boolean contains(K key) 
        {
            return symbolTable[hash(key)].contains(key);
        }

        private void resize(int newSize) 
        {
            SeparateChainingHashTable<K> separateChainingHashTableTemp =
                    new SeparateChainingHashTable<>(newSize, averageListSize);

            for (K key : keys()) 
            {
                separateChainingHashTableTemp.put(key);
            }

            symbolTable = separateChainingHashTableTemp.symbolTable;
            size = separateChainingHashTableTemp.size;
            keysSize = separateChainingHashTableTemp.keysSize;
        }

        public void put(K key) 
        {
            int hashIndex = hash(key);
            int currentSize = symbolTable[hashIndex].size;
            symbolTable[hashIndex].put(key);

            if (currentSize < symbolTable[hashIndex].size)
            {
                ++keysSize;
            }
            if (getLoadFactor() > averageListSize)
            {
                resize(size * 2);
                ++lgM;
            }
        }

        public void delete(K key) 
        {
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

            for (SequentialSearchSymbolTable<K> sequentialSearchST : symbolTable)
            {
                for (K key : sequentialSearchST.keys()) 
                {
                    keys.enqueue(key);
                }
            }
            return keys;
        }
    }

    private SeparateChainingHashTable<T> hashTable;

    public HashSet()
    {
        hashTable = new SeparateChainingHashTable<>();
    }

    public boolean isEmpty() 
    {
        return hashTable.isEmpty();
    }

    public int size() 
    {
        return hashTable.size();
    }

    public boolean contains(T key)
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        return hashTable.contains(key);
    }

    public void add(T key) 
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        hashTable.put(key);
    }

    public void delete(T key)
    {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        hashTable.delete(key);
    }

    public Iterable<T> keys()
    {
        return hashTable.keys();
    }

    @Override
    public String toString() 
    {
        if (isEmpty())
        {
            return "{ }";
        }
        StringBuilder stringBuilder = new StringBuilder("{");

        boolean isFirstK = true;
        for (T key : keys()) 
        {
            if (isFirstK) 
            {
                isFirstK = false;
            } 
            else
            {
                stringBuilder.append(",");
            }
            stringBuilder.append(" ").append(key);
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}