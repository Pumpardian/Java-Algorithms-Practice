package com.task_5_4_xx;

import java.util.ArrayList;
import java.util.List;

import com.task_1_3_12.datastructures.Stack;

public class RegularExpressionMatcher
{
    protected class RangeComplement 
    {
        protected char leftCharacter;
        protected char rightCharacter;

        RangeComplement(char leftCharacter, char rightCharacter)
        {
            this.leftCharacter = leftCharacter;
            this.rightCharacter = rightCharacter;
        }
    }

    protected char[] regularExpression;
    protected Digraph digraph;
    protected int numberOfStates;

    protected SeparateChainingHashTable<Integer, Integer> setsMatchMap;
    protected SeparateChainingHashTable<Integer, HashSet<Character>> setsComplementMap;
    protected SeparateChainingHashTable<Integer, List<RangeComplement>> setsComplementRangesMap;

    public RegularExpressionMatcher(String regularExpressionString)
    {
        Stack<Integer> operators = new Stack<>();
        regularExpression = regularExpressionString.toCharArray();
        numberOfStates = regularExpression.length;

        setsMatchMap = new SeparateChainingHashTable<>();
        setsComplementMap = new SeparateChainingHashTable<>();
        setsComplementRangesMap = new SeparateChainingHashTable<>();

        digraph = new Digraph(numberOfStates + 1);

        for (int i = 0; i < numberOfStates; ++i) 
        {
            int leftOperator = i;

            if (regularExpression[i] == '(' || regularExpression[i] == '|' || regularExpression[i] == '[')
            {
                operators.push(i);
            } 
            else if (regularExpression[i] == ')')
            {
                leftOperator = handleRightParenthesis(operators, i);
            } 
            else if (regularExpression[i] == ']') 
            {
                leftOperator = operators.pop();
                handleSets(leftOperator, i);
            }

            if (i < numberOfStates - 1)
            {
                if (regularExpression[i + 1] == '*') 
                {
                    digraph.addEdge(leftOperator, i + 1);
                    digraph.addEdge(i + 1, leftOperator);
                }
                else if (regularExpression[i + 1] == '+') 
                {
                    digraph.addEdge(i + 1, leftOperator);
                }
            }

            if (regularExpression[i] == '(' || regularExpression[i] == '*' || regularExpression[i] == ')' || regularExpression[i] == '+' || regularExpression[i] == '[' || regularExpression[i] == ']')
            {
                digraph.addEdge(i, i + 1);
            }
        }
    }

    private int handleRightParenthesis(Stack<Integer> operators, int index)
    {
        HashSet<Integer> orOperatorIndexes = new HashSet<>();

        while (regularExpression[operators.peek()] == '|')
        {
            int or = operators.pop();
            orOperatorIndexes.add(or);
        }

        int leftOperator = operators.pop();

        for (int orOperatorIndex : orOperatorIndexes.keys())
        {
            digraph.addEdge(orOperatorIndex, index);
            digraph.addEdge(leftOperator, orOperatorIndex + 1);
        }

        return leftOperator;
    }

    private void handleSets(int leftSquareBracket, int index)
    {
        boolean isComplementSet = false;
        HashSet<Character> charactersToComplement = null;
        List<RangeComplement> rangesToComplement = null;
        
        if (regularExpression[leftSquareBracket + 1] == '^') 
        {
            isComplementSet = true;
            ++leftSquareBracket;
            charactersToComplement = new HashSet<>();
            rangesToComplement = new ArrayList<>();

            int i = leftSquareBracket + 1;
            while (i < index) 
            {
                if (regularExpression[i + 1] == '-') 
                {
                    char leftCharacter = regularExpression[i];
                    char rightCharacter = regularExpression[i + 2];

                    rangesToComplement.add(new RangeComplement(leftCharacter, rightCharacter));
                    i += 2;
                }
                else
                {
                    charactersToComplement.add(regularExpression[i]);
                }
                ++i;
            }
        }
        
        int i = leftSquareBracket + 1;
        while (i < index) 
        {
            digraph.addEdge(leftSquareBracket, i);
            setsMatchMap.put(i, index);

            if (isComplementSet) 
            {
                setsComplementMap.put(i, charactersToComplement);
                if (!rangesToComplement.isEmpty()) 
                {
                    setsComplementRangesMap.put(i, rangesToComplement);
                }
            }

            if (regularExpression[i + 1] == '-') 
            {
                i += 2;
            }
            ++i;
        }
    }

    public boolean recognizes(String text)
    {
        Bag<Integer> allPossibleStates = new Bag<>();
        DirectedDFS directedDFS = new DirectedDFS(digraph, 0);

        for (int vertex = 0; vertex < digraph.verts(); ++vertex)
        {
            if (directedDFS.marked(vertex)) {
                allPossibleStates.add(vertex);
            }
        }

        for (int i = 0; i < text.length(); ++i)
        {
        	Bag<Integer> states = complexityHelper(text, i, allPossibleStates);

            allPossibleStates = new Bag<>();
            directedDFS = new DirectedDFS(digraph, states);

            for (int vertex = 0; vertex < digraph.verts(); ++vertex)
            {
                if (directedDFS.marked(vertex)) 
                {
                    allPossibleStates.add(vertex);
                }
            }
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
    
    private Bag<Integer> complexityHelper(String text, int i, Bag<Integer> allPossibleStates)
    {
        Bag<Integer> states = new Bag<>();

        for (int vertex : allPossibleStates)
        {
            if (vertex < numberOfStates)
            {
                if (setsMatchMap.contains(vertex))
                {
                    recognizeSet(text, i, vertex, states);
                } 
                else if (regularExpression[vertex] == text.charAt(i) || regularExpression[vertex] == '.') 
                {
                    states.add(vertex + 1);
                }
            }
        }
		return states;
    }

    private void recognizeSet(String text, int index, int vertex, Bag<Integer> states) 
    {
        int indexOfRightSquareBracket = setsMatchMap.get(vertex);

        if (regularExpression[vertex + 1] == '-')
        {
            char leftRangeIndex = regularExpression[vertex];
            char rightRangeIndex = regularExpression[vertex + 2];

            if (leftRangeIndex <= text.charAt(index) && text.charAt(index) <= rightRangeIndex)
            {
                if (!isCharPartOfComplementSet(text, index, vertex))
                {
                    states.add(indexOfRightSquareBracket);
                }
            }
            else if (setsComplementMap.contains(vertex) && !isCharPartOfComplementSet(text, index, vertex)) 
            {
                states.add(indexOfRightSquareBracket);
            }
        }
        else if (regularExpression[vertex] == text.charAt(index) || regularExpression[vertex] == '.') 
        {
            if (!isCharPartOfComplementSet(text, index, vertex))
            {
                states.add(indexOfRightSquareBracket);
            }
        }
        else if (setsComplementMap.contains(vertex) && !isCharPartOfComplementSet(text, index, vertex)) 
        {
            states.add(indexOfRightSquareBracket);
        }
    }

    protected boolean isCharPartOfComplementSet(String text, int index, int vertex)
    {
        if (setsComplementMap.contains(vertex) && setsComplementMap.get(vertex).contains(text.charAt(index)))
        {
            return true;
        }

        if (setsComplementRangesMap.contains(vertex))
        {
            for (RangeComplement rangeComplement : setsComplementRangesMap.get(vertex)) 
            {
                if (rangeComplement.leftCharacter <= text.charAt(index) && text.charAt(index) <= rangeComplement.rightCharacter)
                {
                    return true;
                }
            }
        }
        return false;
    }
}