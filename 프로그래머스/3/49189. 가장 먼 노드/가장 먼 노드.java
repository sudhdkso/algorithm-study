import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    private class Node implements Comparable<Node>{
        int index, dist;
        
        public Node(int index, int dist){
            this.index = index;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node e){
            return Integer.compare(dist, e.dist);
        }
    }
    
    private List<Integer>[] graph;
    private int INF = Integer.MAX_VALUE-1;
    public int solution(int n, int[][] edge) {
        int answer = 0, max = 0;
        
        init(n, edge);
        
        int[] dist = dijkstra(n);
        
        for(int i=1;i<=n;i++){
            if(dist[i] == INF) continue;
            if(dist[i] > max){
                max = dist[i];
                answer = 0;
            }
            
            if(dist[i] == max){
                answer++;
            }
        }
        
        return answer;
    }
    
    private void init(int n, int[][] edge){
        graph = new List[n+1];
        
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] e : edge){
            int a = e[0];
            int b = e[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
    }
    
    private int[] dijkstra(int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        
        Arrays.fill(dist, INF);
        
        dist[1] = 0;
        pq.offer(new Node(1, 0));
        
        while(!pq.isEmpty()){
            Node e = pq.poll();
            if(visited[e.index]){
                continue;
            }
            
            visited[e.index] = true;
            
            for(int next : graph[e.index]){
                if(!visited[next] && dist[next] > dist[e.index] + 1){
                    dist[next] = dist[e.index] + 1;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }
        return dist;
    }
    
}