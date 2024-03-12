import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    
    public int solution(int N, int[][] road, int K) {
        int answer = 1;
        
        int[][] graph = new int[N+1][N+1];
        
        for(int i=0;i<road.length;i++){
            int a = road[i][0], b = road[i][1];
            int c = road[i][2];
            if(graph[a][b] == 0){
                graph[a][b] = graph[b][a] = c; 
            }
            else{
                graph[a][b] = graph[b][a] = Math.min(graph[a][b], c);
            }
        }
        
        int[] dist = dijkstra(1, graph, N);
        System.out.println(Arrays.toString(dist));
        for(int i=2;i<=N;i++){
            if(dist[i] <= K){
                answer++;
            }
        }
        
        return answer;
    }
    
    private int[] dijkstra(int start, int[][] graph, int N){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        dist[start] = 0;
        q.offer(start);
        
        while(!q.isEmpty()){
            int index = q.poll();
            
            for(int i=0;i<=N;i++){
                if(graph[index][i] > 0 && dist[i] > dist[index] + graph[index][i]){
                    dist[i] = dist[index] + graph[index][i];
                    q.offer(i);
                }
            }
        }
        return dist;
    }
}