import java.util.*;

public class BFS {

    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        boolean[] marked = new boolean[adj.length];
        int[] distTo = new int[adj.length];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = -1;
        }
        distTo[s] = 0;

        // YOUR CODE HERE
        bfs(adj, s, marked, distTo);

        return distTo[t];
    }

    // Feel free to add a helper method, like the following, to make your code cleaner & modular.
   private static void bfs(ArrayList<Integer>[] adj, int s, boolean[] marked, int[] distTo) {
        Queue<Integer> queue = new LinkedList<Integer>();
        marked[s] = true;
        queue.add(s);
        // ...
        // MORE OF YOUR CODE
        while (!queue.isEmpty()) {
        	int v = queue.remove();
        	for(int i: adj[v]) {
        		if (!marked[i]) {
        			queue.add(i);
        			marked[i] = true;
        			distTo[i] = distTo[v] + 1;
        		}
        	}
        }
        // ...
        
    }


    public static void main(String[] args) {
        In in = new In("G3-2.txt");
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

        int x = in.readInt() - 1;
        int y = in.readInt() - 1;

        System.out.println(distance(adj, x, y));
    }
}
