package com.task_5_4_16.data;

import com.task_1_3_12.datastructures.Stack;
import com.task_5_4_xx.Digraph;
import com.task_5_4_xx.HashSet;
import com.task_5_4_xx.RegularExpressionMatcher;

public class RegularExpressionMatcherMultiwayOr extends RegularExpressionMatcher
{

    public RegularExpressionMatcherMultiwayOr(String regularExpressionString)
    {
        super(regularExpressionString);

        Stack<Integer> operators = new Stack<>();
        regularExpression = regularExpressionString.toCharArray();
        numberOfStates = regularExpression.length;
        digraph = new Digraph(numberOfStates + 1);

        for (int i = 0; i < numberOfStates; ++i) 
        {
            int leftParenthesis = i;

            if (regularExpression[i] == '(' || regularExpression[i] == '|')
            {
                operators.push(i);
            } 
            else if (regularExpression[i] == ')')
            {
            	leftParenthesis = complexityHelper(operators, i);
            }

            if (i < numberOfStates - 1 && regularExpression[i + 1] == '*')
            {
                digraph.addEdge(leftParenthesis, i + 1);
                digraph.addEdge(i + 1, leftParenthesis);
            }

            if (regularExpression[i] == '(' || regularExpression[i] == '*' || regularExpression[i] == ')')
            {
                digraph.addEdge(i, i + 1);
            }
        }
    }
    
    private int complexityHelper(Stack<Integer> operators, int i)
    {
        HashSet<Integer> orOperatorIndexes = new HashSet<>();

        while (regularExpression[operators.peek()] == '|') 
        {
            int or = operators.pop();
            orOperatorIndexes.add(or);
        }

        int leftParenthesis = operators.pop();

        for (int orOperatorIndex : orOperatorIndexes.keys())
        {
            digraph.addEdge(orOperatorIndex, i);
            digraph.addEdge(leftParenthesis, orOperatorIndex + 1);
        }
        
        return leftParenthesis;
    }
}
