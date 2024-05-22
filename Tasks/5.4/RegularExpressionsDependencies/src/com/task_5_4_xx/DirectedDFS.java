package com.task_5_4_xx;

public class DirectedDFS
{
    private boolean[] visited;

    public DirectedDFS(Digraph digraph, int source) 
    {
        visited = new boolean[digraph.verts()];
        dfs(digraph, source);
    }

    public DirectedDFS(Digraph digraph, Iterable<Integer> sources)
    {
        visited = new boolean[digraph.verts()];

        for (int source : sources) 
        {
            if (!visited[source])
            {
                dfs(digraph, source);
            }
        }
    }

    private void dfs(Digraph digraph, int source)
    {
        visited[source] = true;

        for (int neighbor : digraph.adj(source))
        {
            if (!visited[neighbor]) {
                dfs(digraph, neighbor);
            }
        }
    }

    public boolean marked(int vertex)
    {
        return visited[vertex];
    }
}
