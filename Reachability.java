import java.util.ArrayList;
import java.util.Arrays;

public class Reachability {
	
	//checks for a pth from given cell to given exit
	//path between u and v
	public static boolean[] visited;
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
    	
    	
    	//all vertices that are adjacent to vertex
    	for (int i: adj[x]) {
    		if (! visited[i]) {
    			visited[i] = true;
    			dfs(adj, i);
    		}
    	}

        // YOUR CODE HERE
    		if (visited[y]) //if depth first search is visited, return 1 else return 0
        		return 1;
        	else


        return 0;  // <- change this line to return the correct result
    }

    // Hint: review depth-first search (dfs). It may be 'cleaner' to write a
    // separate helper method (dfs) and call it from your code in reach().
    // Alternatively, you can keep all your logic in reach(), if you wish.

    
    //traversal vertices reachable from v
    private static void dfs(ArrayList<Integer>[] adj, int v) { 
    	for (int i: adj[v]) {
    		if (!visited[i]) {
    			visited[i] = true;
    			//calling recursive helper
    			dfs(adj, i);
    		}
    	}
    }


    public static void main(String[] args) {
        In in = new In("G1-2.txt");
        int n = in.readInt();  // number of vertices
        int m = in.readInt();  // number of edges

        // for each vertex, allocate space for its adjacency list
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        visited = new boolean[n];
        
        // read the next m lines of input and build an
        // adjacency list representation of the graph
        for (int i = 0; i < m; i++) {
            int x, y;
            x = in.readInt();
            y = in.readInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        //System.out.println(Arrays.toString(adj) + "\n");

        // read the last line of the input file.
        // x = the start vertex; y = the end vertex
        int x = in.readInt() - 1;
        int y = in.readInt() - 1;
        // is y reachable from x
        System.out.println(reach(adj, x, y));
    }
}
