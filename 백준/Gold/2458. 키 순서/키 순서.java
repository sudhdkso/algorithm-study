import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer>[] adj,rev;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adj = new List[N+1];
		rev = new List[N+1];
		for(int i=1;i<=N;i++){
			adj[i] = new ArrayList<>();
			rev[i] = new ArrayList<>();
		}

		while(M-->0){
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a].add(b);
			rev[b].add(a);
		}
		int count = 0;
		for(int i=1;i<=N;i++){
			int bigger = bfs(i, adj);
			int smaller = bfs(i, rev);
			if(bigger+smaller == N-1) count++;
		}

		bw.write(count+"\n");
		bw.flush();
		bw.close();
	}

	private static int bfs(int start, List<Integer>[] graph){
		Queue<Integer> pq = new LinkedList<>();
		boolean[] visited = new boolean[N+1];

		visited[start] = true;
		pq.offer(start);
		int count = 0;
		while(!pq.isEmpty()){
			int now = pq.poll();

			for(int next: graph[now]){
				if(!visited[next]){
					visited[next] = true;
					pq.offer(next);
					count++;
				}
			}
		}
		return count;
	}

}