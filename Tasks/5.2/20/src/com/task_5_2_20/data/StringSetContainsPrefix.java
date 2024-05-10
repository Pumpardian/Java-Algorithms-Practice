package com.task_5_2_20.data;

import com.task_5_2_xx.data.StringSet;

public class StringSetContainsPrefix extends StringSet
{

    public boolean containsPrefix(String prefix)
    {
        return containsPrefix(root, prefix, 0);
    }

    private boolean containsPrefix(Node node, String prefix, int digit) 
    {
        if (node == null)
        {
            return false;
        }
        if (digit == prefix.length()) 
        {
            return true;
        }
        char nextChar = prefix.charAt(digit);
        return containsPrefix(node.getNext().get(nextChar), prefix, digit + 1);
    }
}
