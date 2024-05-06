package com.task_5_2_9.data;

import com.task_1_3_37.datastructures.Queue;
import com.task_5_2_xx.data.TernarySearchTrie;

public class TSTExtended<V> extends TernarySearchTrie<V>
{
    public Iterable<String> keys()
    {
        Queue<String> keys = new Queue<>();
        collect(root, new StringBuilder(), keys);
        return keys;
    }

    public Iterable<String> keysWithPrefix(String prefix)
    {
        if (prefix == null) 
        {
            throw new IllegalArgumentException();
        }
        
        Queue<String> keysWithPrefix = new Queue<>();
        Node nodeWithPrefix = get(root, prefix, 0);
        if (nodeWithPrefix == null) 
        {
            return keysWithPrefix;
        }
        if (nodeWithPrefix.getValue() != null)
        {
            keysWithPrefix.enqueue(prefix);
        }

        collect(nodeWithPrefix.getMiddle(), new StringBuilder(prefix), keysWithPrefix);
        return keysWithPrefix;
    }

    public Iterable<String> keysThatMatch(String pattern)
    {
        if (pattern == null)
        {
            throw new IllegalArgumentException();
        }
        Queue<String> keysThatMatch = new Queue<>();
        collect(root, new StringBuilder(), pattern, keysThatMatch);
        return keysThatMatch;
    }

    public String longestPrefixOf(String query) 
    {
        if (query == null)
        {
            throw new IllegalArgumentException();
        }

        int length = search(root, query, 0, 0);
        return query.substring(0, length);
    }
}
