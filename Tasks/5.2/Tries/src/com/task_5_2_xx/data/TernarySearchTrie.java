package com.task_5_2_xx.data;

import com.task_1_3_37.datastructures.Queue;

public class TernarySearchTrie<V>
{
    private int size;
    protected Node root;

    protected class Node
    {
        private char character;
        private V value;
        int size;

        private Node left;
        private Node middle;
        private Node right;
        
		public char getCharacter()
		{
			return character;
		}
		public void setCharacter(char character)
		{
			this.character = character;
		}
		
		public Node getLeft() 
		{
			return left;
		}
		
		public void setLeft(Node left)
		{
			this.left = left;
		}
		
		public Node getRight() 
		{
			return right;
		}
		
		public void setRight(Node right)
		{
			this.right = right;
		}
		
		public V getValue() 
		{
			return value;
		}
		
		public void setValue(V value) 
		{
			this.value = value;
		}
		
		public Node getMiddle()
		{
			return middle;
		}
		
		public void setMiddle(Node middle)
		{
			this.middle = middle;
		}
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public boolean contains(String key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    public V get(String key)
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        if (key.length() == 0) 
        {
            throw new IllegalArgumentException();
        }

        Node node = get(root, key, 0);
        if (node == null) 
        {
            return null;
        }
        return node.getValue();
    }

    protected Node get(Node node, String key, int digit)
    {
        if (node == null)
        {
            return null;
        }

        char currentChar = key.charAt(digit);

        if (currentChar < node.getCharacter())
        {
            return get(node.getLeft(), key, digit);
        }
        else if (currentChar > node.getCharacter())
        {
            return get(node.getRight(), key, digit);
        }
        else if (digit < key.length() - 1) 
        {
            return get(node.getMiddle(), key, digit + 1);
        }
        else 
        {
            return node;
        }
    }

    public void put(String key, V value)
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

        boolean isNewKey = false;
        if (!contains(key))
        {
            isNewKey = true;
        }

        root = put(root, key, value, 0, isNewKey);
    }

    private Node put(Node node, String key, V value, int digit, boolean isNewKey)
    {
        char currentChar = key.charAt(digit);
        if (node == null) 
        {
            node = new Node();
            node.setCharacter(currentChar);
        }

        if (currentChar < node.getCharacter()) 
        {
            node.setLeft(put(node.getLeft(), key, value, digit, isNewKey));
        }
        else if (currentChar > node.getCharacter()) 
        {
            node.setRight(put(node.getRight(), key, value, digit, isNewKey));
        } 
        else if (digit < key.length() - 1)
        {
            node.setMiddle(put(node.getMiddle(), key, value, digit + 1, isNewKey));

            if (isNewKey)
            {
                node.size = node.size + 1;
            }
        }
        else 
        {
            node.setValue(value);

            if (isNewKey)
            {
                node.size = node.size + 1;
                ++size;
            }
        }
        return node;
    }

    public void delete(String key) 
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        if (!contains(key)) 
        {
            return;
        }

        root = delete(root, key, 0);
        --size;
    }

    private Node delete(Node node, String key, int digit) 
    {
        if (node == null)
        {
            return null;
        }
        if (digit == key.length() - 1)
        {
            node.size = node.size - 1;
            node.setValue(null);
        }
        else
        {
            char nextChar = key.charAt(digit);
            if (nextChar < node.getCharacter()) 
            {
                node.setLeft(delete(node.getLeft(), key, digit));
            }
            else if (nextChar > node.getCharacter())
            {
                node.setRight(delete(node.getRight(), key, digit));
            }
            else 
            {
                node.size = node.size - 1;
                node.setMiddle(delete(node.getMiddle(), key, digit + 1));
            }
        }

        if (node.size == 0) 
        {
            if (node.getLeft() == null && node.getRight() == null)
            {
                return null;
            } 
            else if (node.getLeft() == null)
            {
                return node.getRight();
            } 
            else if (node.getRight() == null) 
            {
                return node.getLeft();
            } 
            else
            {
                Node aux = node;
                node = min(aux.getRight());
                node.setRight(deleteMin(aux.getRight()));
                node.setLeft(aux.getLeft());
            }
        }

        return node;
    }

    protected void collect(Node node, StringBuilder prefix, Queue<String> queue)
    {
        if (node == null)
        {
            return;
        }

        collect(node.getLeft(), prefix, queue);
        if (node.getValue() != null)
        {
            queue.enqueue(prefix.toString() + node.getCharacter());
        }
        collect(node.getMiddle(), prefix.append(node.getCharacter()), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(node.getRight(), prefix, queue);
    }

    protected void collect(Node node, StringBuilder prefix, String pattern, Queue<String> queue) 
    {
        if (node == null) 
        {
            return;
        }

        int digit = prefix.length();
        char nextCharInPattern = pattern.charAt(digit);
        if (nextCharInPattern == '.' || nextCharInPattern < node.getCharacter()) 
        {
            collect(node.getLeft(), prefix, pattern, queue);
        }
        if (nextCharInPattern == '.' || nextCharInPattern == node.getCharacter())
        {
            if (digit == pattern.length() - 1 && node.getValue() != null)
            {
                queue.enqueue(prefix.toString() + node.getCharacter());
            } 
            else if (digit < pattern.length() - 1) 
            {
                collect(node.getMiddle(), prefix.append(node.getCharacter()), pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (nextCharInPattern == '.' || nextCharInPattern > node.getCharacter())
        {
            collect(node.getRight(), prefix, pattern, queue);
        }
    }

    protected int search(Node node, String query, int digit, int length)
    {
        if (node == null)
        {
            return length;
        }
        if (node.getValue() != null && node.getCharacter() == query.charAt(digit))
        {
            length = digit + 1;
        }

        char nextChar = query.charAt(digit);
        if (nextChar < node.getCharacter())
        {
            return search(node.getLeft(), query, digit, length);
        } 
        else if (nextChar > node.getCharacter()) 
        {
            return search(node.getRight(), query, digit, length);
        }
        else if (digit < query.length() - 1)
        {
            return search(node.getMiddle(), query, digit + 1, length);
        }
        else 
        {
            return length;
        }
    }
    
    public String floor(String key) 
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }

        return floor(root, key, 0, new StringBuilder(), null, true);
    }

    private String floor(Node node, String key, int digit, StringBuilder prefix, String lastKeyFound, boolean mustBeEqualDigit)
    {
        if (node == null)
        {
            return lastKeyFound;
        }
        
        char currentChar;
        if (digit < key.length() && mustBeEqualDigit)
        {
            currentChar = key.charAt(digit);
        } 
        else
        {
            currentChar = Character.MAX_VALUE;
            mustBeEqualDigit = false;
        }

        if (currentChar < node.getCharacter() && mustBeEqualDigit)
        {
            return floor(node.getLeft(), key, digit, prefix, lastKeyFound, true);
        }
        else if (!mustBeEqualDigit || currentChar >= node.getCharacter()) 
        {
            floorComplexityHelper(node, key, digit, prefix, lastKeyFound, mustBeEqualDigit, currentChar);
        }

        return null;
    }
    
    private String floorComplexityHelper(Node node, String key, int digit, StringBuilder prefix, String lastKeyFound, boolean mustBeEqualDigit, char currentChar)
    {
    	StringBuilder prefixWithCharacter = new StringBuilder(prefix).append(node.getCharacter());
        if (prefixWithCharacter.toString().compareTo(key) > 0) 
        {

            if (node.getLeft() != null) 
            {
                return floor(node.getLeft(), key, digit, prefix, lastKeyFound, mustBeEqualDigit);
            }
            return lastKeyFound;
        }
    	if (mustBeEqualDigit && currentChar > node.getCharacter())
        {
            mustBeEqualDigit = false;
        }
        String rightKey = floor(node.getRight(), key, digit, prefix, lastKeyFound, mustBeEqualDigit);
        if (rightKey != null)
        {
            return rightKey;
        }

        String middleKey = floor(node.getMiddle(), key, digit + 1, prefixWithCharacter, null, mustBeEqualDigit);
        if (middleKey != null) 
        {
            return middleKey;
        }

        if (node.getValue() != null && prefixWithCharacter.toString().compareTo(key) <= 0)
        {
            return prefixWithCharacter.toString();
        }

        String leftKey = floor(node.getLeft(), key, digit, prefix, lastKeyFound, mustBeEqualDigit);
        if (leftKey != null)
        {
            return leftKey;
        }
        
        return null;
    }
    
    public String ceiling(String key)
    {
        if (key == null) 
        {
            throw new IllegalArgumentException();
        }
        return ceiling(root, key, 0, new StringBuilder(), null, true);
    }

    private String ceiling(Node node, String key, int digit, StringBuilder prefix, String lastKeyFound, boolean mustBeEqualDigit) {
        if (node == null)
        {
            return lastKeyFound;
        }

        char currentChar;
        if (digit < key.length() && mustBeEqualDigit)
        {
            currentChar = key.charAt(digit);
        } 
        else 
        {
            currentChar = 0;
            mustBeEqualDigit = false;
        }

        if (currentChar > node.getCharacter() && mustBeEqualDigit)
        {
            return ceiling(node.getRight(), key, digit, prefix, lastKeyFound, true);
        }
        else if (!mustBeEqualDigit || currentChar <= node.getCharacter()) 
        {
        	ceilingComplexityHelper(node, key, digit, prefix, mustBeEqualDigit, currentChar);
        }

        return null;
    }
    
    private String ceilingComplexityHelper(Node node, String key, int digit, StringBuilder prefix, boolean mustBeEqualDigit, char currentChar)
    {
    	StringBuilder prefixWithCharacter = new StringBuilder(prefix).append(node.getCharacter());
        if (mustBeEqualDigit && currentChar < node.getCharacter()) 
        {
            mustBeEqualDigit = false;
        }

        if (!mustBeEqualDigit) 
        {
            String lastKeyFound = ceiling(node.getLeft(), key, digit, prefix, null, false);
            if (lastKeyFound != null)
            {
                return lastKeyFound;
            }
        }

        if (node.getValue() != null && prefixWithCharacter.toString().compareTo(key) >= 0)
        {
            return prefixWithCharacter.toString();
        }

        String middleKey = ceiling(node.getMiddle(), key, digit + 1, prefixWithCharacter, null, mustBeEqualDigit);
        if (middleKey != null)
        {
            return middleKey;
        }

        String rightKey = ceiling(node.getRight(), key, digit, prefix, null, mustBeEqualDigit);
        if (rightKey != null) 
        {
            return rightKey;
        }
        return null;
    }

    public String select(int index)
    {
        if (index < 0 || index >= size())
        {
            throw new IllegalArgumentException();
        }
        return select(root, index, new StringBuilder());
    }

    private String select(Node node, int index, StringBuilder prefix)
    {
        if (node == null) 
        {
            return null;
        }

        int leftSubtreeSize = getTreeSize(node.getLeft());
        int tstSize = leftSubtreeSize + node.size;
        if (index < leftSubtreeSize)
        {
            return select(node.getLeft(), index, prefix);
        }
        else if (index >= tstSize)
        {
            return select(node.getRight(), index - tstSize, prefix);
        }
        else 
        {
            index = index - leftSubtreeSize;

            if (node.getValue() != null) 
            {
                if (index == 0) 
                {
                    return prefix.append(node.getCharacter()).toString();
                }
                --index;
            }
            prefix.append(node.getCharacter());
            return select(node.getMiddle(), index, prefix);
        }
    }

    public int rank(String key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException();
        }
        return rank(root, key, 0, 0);
    }

    private int rank(Node node, String key, int digit, int size)
    {
        if (node == null) 
        {
            return size;
        }

        char currentChar = key.charAt(digit);
        if (currentChar < node.getCharacter())
        {
            return rank(node.getLeft(), key, digit, size);
        } 
        else 
        {
            if (currentChar > node.getCharacter()) 
            {
                if (node.getValue() != null) 
                {
                    ++size;
                }

                return getTreeSize(node.getLeft()) + getTreeSize(node.getMiddle()) + rank(node.getRight(), key, digit, size);
            }
            else if (digit < key.length() - 1) 
            {
                if (digit < key.length() - 1 && node.getValue() != null) 
                {
                    ++size;
                }
                return getTreeSize(node.getLeft()) + rank(node.getMiddle(), key, digit + 1, size);
            }
            else
            {
                return getTreeSize(node.getLeft()) + size;
            }
        }
    }

    private int getTreeSize(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        int treeSize = node.size;
        treeSize += getTreeSize(node.getLeft());
        treeSize += getTreeSize(node.getRight());
        return treeSize;
    }

    public String min()
    {
        if (isEmpty())
        {
            return null;
        }

        Node minNode = min(root);

        StringBuilder minKey = new StringBuilder();
        minKey.append(minNode.getCharacter());

        while (minNode.getValue() == null)
        {
            minNode = minNode.getMiddle();
            while (minNode.getLeft() != null) 
            {
                minNode = minNode.getLeft();
            }
            minKey.append(minNode.getCharacter());
        }
        return minKey.toString();
    }

    private Node min(Node node)
    {
        if (node.getLeft() == null)
        {
            return node;
        }
        return min(node.getLeft());
    }

    public String max()
    {
        if (isEmpty()) 
        {
            return null;
        }

        Node maxNode = max(root);
        StringBuilder maxKey = new StringBuilder();
        maxKey.append(maxNode.getCharacter());
        while (maxNode.size != 1 || maxNode.getValue() == null)
        {
            maxNode = maxNode.getMiddle();
            while (maxNode.getRight() != null)
            {
                maxNode = maxNode.getRight();
            }
            maxKey.append(maxNode.getCharacter());
        }
        return maxKey.toString();
    }

    private Node max(Node node) 
    {
        if (node.getRight() == null)
        {
            return node;
        }
        return max(node.getRight());
    }

    public void deleteMin() 
    {
        if (isEmpty())
        {
            return;
        }
        String minKey = min();
        delete(minKey);
    }
    
    private Node deleteMin(Node node)
    {
        if (node.getLeft() == null) 
        {
            return node.getRight();
        }
        node.setLeft(deleteMin(node.getLeft()));
        return node;
    }

    public void deleteMax()
    {
        if (isEmpty()) 
        {
            return;
        }
        String maxKey = max();
        delete(maxKey);
    }
}
