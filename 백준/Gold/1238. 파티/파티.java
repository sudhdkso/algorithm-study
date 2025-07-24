import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int to, cost;
		public Node(int to, int cost){
			this.to = to;
			this.cost =cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<Node>[] graph = new ArrayList[N+1];
		List<Node>[] dGraph = new ArrayList[N+1];

		for(int i=1;i<=N;i++){
			graph[i] = new ArrayList<>();
			dGraph[i] = new ArrayList<>();
		}

		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph[A].add(new Node(B,C));
			dGraph[B].add(new Node(A,C));
		}

		int[] dToX = dijkstra(X, N, graph);
		int[] dFromX = dijkstra(X, N,dGraph);

		int answer = 0;
		for(int i=1;i<=N;i++){
			answer = Math.max(answer, dToX[i] +dFromX[i]);
		}
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
	}

	public static int[] dijkstra(int start,int N, List<Node>[] graph){
		PriorityQueue<Node> q = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.offer(new Node(start, 0));
		dist[start] = 0;

		while(!q.isEmpty()){
			Node now = q.poll();
			for(Node next : graph[now.to]){
				if(dist[next.to] > dist[now.to] + next.cost){
					dist[next.to] = dist[now.to] + next.cost;
					q.offer(new Node(next.to, dist[next.to]));
				}
			}
		}
		return dist;
	}
}