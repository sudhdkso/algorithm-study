import java.util.*;
class Solution {
    private class Node implements Comparable<Node>{
        int index, cost;
        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node e){
            return Integer.compare(this.cost, e.cost);
        }
    }
    private int INF = Integer.MAX_VALUE-1;
    private List<Integer>[] graph;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        graph = new List[n+1];
        for(int i=0;i<=n;i++){
           graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<roads.length;i++){
            int a = roads[i][0];
            int b = roads[i][1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        int[] dist = dijkstra(destination, n);
        
        for(int i=0;i<sources.length;i++){
            answer[i] = dist[sources[i]] == INF ? -1 : dist[sources[i]];
        }
        return answer;
    }
    
    private int[] dijkstra(int d, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        
        Arrays.fill(dist, INF);
        dist[d] = 0;
        
        pq.offer(new Node(d,0));
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int index = now.index;
            int cost = now.cost;
            if(dist[index] > cost + 1) continue;
            for(int i : graph[index]){
                if(dist[i] > cost + 1){
                    
                    dist[i] = cost+1;
                    pq.offer(new Node(i, dist[i]));
                }
            }
        }
        return dist;
    }
}