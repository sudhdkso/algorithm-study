import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int to,weight;

		public Node(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	static final int INF = 1_000_001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); //도시의 개수
		int M = Integer.parseInt(st.nextToken()); //도로의 개수

		int[][] graph = new int[N+1][N+1];


		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], INF);
			graph[i][i] = 0;
		}

		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			graph[A][B] = Math.min(graph[A][B], C);
		}

		for(int k=1;k<=N;k++){
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(graph[i][k] != INF && graph[k][j] != INF){
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}
		}
		int K = Integer.parseInt(br.readLine());

		int[] friend = new int[K];
		st = new StringTokenizer(br.readLine()," ");

		for(int i=0;i<K;i++){
			friend[i] = Integer.parseInt(st.nextToken());
		}


		int[] time = new int[N+1];
		int min = Integer.MAX_VALUE;
		for(int k=1;k<=N;k++){
			for(int f=0;f<K;f++){
				int city = friend[f];
				if(graph[city][k] == INF || graph[k][city] == INF) continue;
				time[k] = Math.max(time[k], graph[city][k] + graph[k][city]);
			}
			if(time[k] == 0) continue;
			min = Math.min(min, time[k]);
		}

		for(int i=1;i<=N;i++){
			if(time[i] == min){
				bw.write(i+" ");
			}
		}
		bw.flush();
		bw.close();
	}

}