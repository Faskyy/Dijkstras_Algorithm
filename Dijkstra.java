import java.util.*;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	
    	//initialized dist[] by int
    	//dist[v] is an upper bound on the actual distance from s to v
    	int n = adj.length;
    	int[] dist = new int[n];
    	int[] prev = new int[n];
    	boolean[] visited = new boolean[n];
    	for (int i = 0; i < n; i++) {
    		dist[i] = Integer.MAX_VALUE;
    		prev[i] = -1;
    	}
    	dist[s] = 0;
    	  
    	// Traverse each vertex outside of known region R.
        for (int j = 0; j < n; j++) {
            int u = minDistVertex(dist, visited);
            // BZ: returned minVertex is -1? unreachable from S.
            if (u == -1) continue;
            visited[u] = true;
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i), w = cost[u].get(i);
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    // BZ: update previous node of v as well for reconstruction?
                    prev[v] = u;
                }
            }
        }
       
        // BZ: no need to reconstruct.
        // After the call to algorithm, all the distances are set correctly.
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }

    public static int minDistVertex(int[] dist, boolean[] visited) {
        /**Find the vertex with min dist and not yet visited.*/
        int minDist = Integer.MAX_VALUE, minVertex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (visited[v]) continue;
            if (dist[v] < minDist) minVertex = v;
            minDist = Math.min(minDist, dist[v]);
        }
        return minVertex;
    }

    
    private static class DistNode {
        int v;
        int dist;
        public DistNode(int v, int d) {
            this.v = v;
            this.dist = d;
        }
    }
    private static int distance_faster(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int n = adj.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<DistNode> pq = new PriorityQueue<>(new Comparator<DistNode>(){
            @Override
            public int compare(DistNode v1, DistNode v2) {
                // BZ: v1.dist - v2.dist? if v2.dist is +inf? overflow!
                return v1.dist < v2.dist ? -1 : 1;
            }
        });
        for (int i = 1; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            pq.offer(new DistNode(i, Integer.MAX_VALUE));
        }
        dist[0] = 0;
        pq.offer(new DistNode(0, 0));
        while (! pq.isEmpty()) {
            DistNode u = pq.poll();
            if (visited[u.v]) continue;
            visited[u.v] = true;
            for (int i = 0; i < adj[u.v].size(); i++) {
                int next = adj[u.v].get(i), w = cost[u.v].get(i);
                if (dist[next] > dist[u.v] + w) {
                    dist[next] = dist[u.v] + w;
                    pq.offer(new DistNode(next, dist[next]));
                }
            }
        }
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    
       
    }

    public static void main(String[] args) {
        In in = new In("G4-3.txt");
        int n = in.readInt();
        int m = in.readInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = in.readInt();
            y = in.readInt();
            w = in.readInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = in.readInt() - 1;
        int y = in.readInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}
