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
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		List<Integer>[] graph = new List[N+1];

		for(int i=1;i<=N;i++){
			graph[i] = new ArrayList<>();
		}

		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(A == -1 && B == -1) break;

			graph[A].add(B);
			graph[B].add(A);
		}
		StringBuilder sb = new StringBuilder();
		int min = Integer.MAX_VALUE-1, count = 0;
		for(int i=1;i<=N;i++){
			int result = bfs(i, graph);
			if(result < min){
				min = result;
				sb.delete(0, sb.length());
				sb.append(i).append(" ");
				count = 1;
			}
			else if(result == min){
				count++;
				sb.append(i).append(" ");
			}
		}

		bw.write(min+" "+count+"\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static int bfs(int start, List<Integer>[] graph){
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE-1);

		dist[start] = 0;
		visited[start] = true;
		q.offer(start);

		while(!q.isEmpty()){
			int cur = q.poll();

			for(int next: graph[cur]){
				if(!visited[next] && dist[next] > dist[cur]+1){
					visited[next] = true;
					dist[next] = dist[cur] + 1;
					q.offer(next);
				}
			}
		}
		int max = 0;
		for(int i=1;i<=N;i++){
			if(dist[i] == Integer.MAX_VALUE-1) continue;
			max = Math.max(max, dist[i]);
		}
 		return max;
	}
}