package com.task_5_4_xx;

import com.task_1_3_12.datastructures.Stack;

public class Digraph
{
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int verts;
	private int edges;
	private Bag<Integer>[] adjacency;
	private int[] indegree;
	
    @SuppressWarnings("unchecked")
	public Digraph(int verts)
    {
        if (verts < 0)
    	{
        	throw new IllegalArgumentException();
    	}
        
        this.verts = verts;
        this.edges = 0;
        indegree = new int[verts];
        adjacency = new Bag[verts];
        for (int i = 0; i < verts; ++i)
        {
            adjacency[i] = new Bag<>();
        }
    }
    
    @SuppressWarnings("unchecked")
	public Digraph(Digraph graph)
    {
        if (graph == null) 
    	{
    		throw new IllegalArgumentException();
    	}

        this.verts = graph.verts();
        this.edges = graph.edges();
        if (verts < 0)
    	{
    		throw new IllegalArgumentException();
    	}

        indegree = new int[verts];
        for (int i = 0; i < verts; ++i)
        {
    		this.indegree[i] = graph.indegree(i);
        }
        
        adjacency = new Bag[verts];
        for (int i = 0; i < verts; ++i) 
        {
            adjacency[i] = new Bag<>();
        }

        for (int i = 0; i < graph.verts(); ++i)
        {
        	Stack<Integer> reverse = new Stack<>();
            for (int v : graph.adjacency[i])
            {
                reverse.push(v);
            }
            for (int v : reverse)
            {
                adjacency[i].add(v);
            }
        }
    }
    
    public int verts()
    {
        return verts;
    }
    
    public int edges()
    {
        return edges;
    }
    
    private void validateVertex(int v)
    {
        if (v < 0 || v >= verts)
        {
            throw new IllegalArgumentException();
        }
    }
    
    public void addEdge(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        adjacency[v].add(w);
        indegree[w]++;
        ++edges;
    }
    
    public Iterable<Integer> adj(int v)
    {
        validateVertex(v);
        return adjacency[v];
    }
    
    public int outdegree(int v)
    {
        validateVertex(v);
        return adjacency[v].size();
    }
    
    public int indegree(int v)
    {
        validateVertex(v);
        return indegree[v];
    }
    
    public Digraph reverse()
    {
        Digraph reverse = new Digraph(verts);
        for (int i = 0; i < verts; ++i)
        {
            for (int v : adj(verts))
            {
                reverse.addEdge(v, verts);
            }
        }
        return reverse;
    }
    
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(verts + " вершин, " + edges + " ребер " + NEWLINE);
        for (int i = 0; i < verts; ++i)
        {
            str.append(String.format("%d: ", verts));
            for (int v : adjacency[verts])
            {
                str.append(String.format("%d ", v));
            }
            str.append(NEWLINE);
        }
        return str.toString();
    }
}
