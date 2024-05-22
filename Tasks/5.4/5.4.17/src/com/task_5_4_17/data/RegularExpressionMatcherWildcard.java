package com.task_5_4_17.data;

import com.task_5_4_xx.Bag;
import com.task_5_4_xx.DirectedDFS;
import com.task_5_4_xx.RegularExpressionMatcher;

public class RegularExpressionMatcherWildcard extends RegularExpressionMatcher 
{
    public RegularExpressionMatcherWildcard(String regularExpressionString) 
    {
        super(regularExpressionString);
    }

    @Override
    public boolean recognizes(String text)
    {
        Bag<Integer> allPossibleStates = new Bag<>();
        DirectedDFS directedDFS = new DirectedDFS(digraph, 0);

        for (int vertex = 0; vertex < digraph.verts(); ++vertex)
        {
            if (directedDFS.marked(vertex))
            {
                allPossibleStates.add(vertex);
            }
        }

        for (int i = 0; i < text.length(); ++i)
        {
        	allPossibleStates = complexityHelper(allPossibleStates, text, i);
        	
            if (allPossibleStates.size() == 0)
            {
                return false;
            }
        }

        for (int vertex : allPossibleStates)
        {
            if (vertex == numberOfStates) 
            {
                return true;
            }
        }
        return false;
    }
    
    private Bag<Integer> complexityHelper(Bag<Integer> allPossibleStates, String text, int i)
    {
        Bag<Integer> states = new Bag<>();

        for (int vertex : allPossibleStates) 
        {
            if (vertex < numberOfStates &&  (regularExpression[vertex] == text.charAt(i) || regularExpression[vertex] == '.'))
            {
            	states.add(vertex + 1);
            }
        }

        allPossibleStates = new Bag<>();
        DirectedDFS directedDFS = new DirectedDFS(digraph, states);

        for (int vertex = 0; vertex < digraph.verts(); ++vertex)
        {
            if (directedDFS.marked(vertex))
            {
                allPossibleStates.add(vertex);
            }
        }
        
        return allPossibleStates;
    }
}
