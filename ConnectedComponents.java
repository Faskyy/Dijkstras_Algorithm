import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {

    	//connected components of undirected graph
        // YOUR CODE HERE
    	boolean[] marked = new boolean[adj.length];
    	int result = 1;
    	
    	for (int i = 0; i < adj.length; i++) {
    		if (! marked[i]) {
    			dfs(i, adj, marked); 
    			
    			//new connected components
    			result++;
    		}
    	}

        return result -1; // <- change this line to return the correct result
    }

    private static void dfs(int vertex, ArrayList<Integer>[] adj, boolean[] marked) {
    	
    	//all connected vertices have same connected components
    	marked[vertex] = true;
    	for (int neighbor : adj[vertex]) {
    		if (! marked[neighbor])
    			dfs(neighbor, adj, marked);
    	}
    }
    // feel free to add a helper method to make your code cleaner & modular.


    public static void main(String[] args) {
        In in = new In("G2-3.txt");
        int n = in.readInt();
        int m = in.readInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = in.readInt();
            y = in.readInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}
