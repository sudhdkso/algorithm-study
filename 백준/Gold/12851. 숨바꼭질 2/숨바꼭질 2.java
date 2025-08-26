import java.io.*;
import java.util.*;

public class Main {
    static class Coordi{
        int x, time;

        public Coordi(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    static int count = 0;
    private static void bfs(int N, int K){
        PriorityQueue<Coordi> pq = new PriorityQueue<>((o1,o2) -> o1.time-o2.time);
        pq.offer(new Coordi(N,0));
        visited[N] = true;
        while(!pq.isEmpty()){
            Coordi cd = pq.poll();
            visited[cd.x] = true;
            if(cd.x == K){
                if(min == cd.time){
                    count++;
                }
                if(min > cd.time){
                    min = cd.time;
                    count = 1;
                }
            }
            if(cd.x*2 <=100_001 && !visited[cd.x*2]){
                pq.offer(new Coordi(cd.x*2,cd.time+1));
            }
            if(cd.x+1 <= 100_001 && !visited[cd.x+1]){
                pq.offer(new Coordi(cd.x+1,cd.time+1));
            }
            if(cd.x-1 >= 0 && !visited[cd.x-1]){
                pq.offer(new Coordi(cd.x-1,cd.time+1));
            }
        }

    }
    public static void main (String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        visited = new boolean[100_002];
        bfs(N,K);
        bw.write(min+"\n"+count);
        bw.flush();
    }

}