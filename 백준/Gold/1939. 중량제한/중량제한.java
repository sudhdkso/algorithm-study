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

	static class Edge {
		int to, weight;

		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	private static int src = 0, dest = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); //섬의 갯수
		int M = Integer.parseInt(st.nextToken()); //다리 정보의 수

		List<Edge>[] graph = new List[N+1];

		for(int i=0;i<=N;i++){
			graph[i] = new ArrayList<>();
		}

		long left = 0, right = 0;
		while(M-- > 0){
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			right += C;
			graph[A].add(new Edge(B,C));
			graph[B].add(new Edge(A,C));
		}

		st = new StringTokenizer(br.readLine()," ");
		src = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());

		while(left <= right){
			long mid = (left+right)/2;
			if(isCross(mid, graph)){
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}

		bw.write(right+"\n");
		bw.flush();
		bw.close();
	}


	private static  boolean isCross(long threshold, List<Edge>[] graph){
		return bfs(graph, threshold);
	}

	public static boolean bfs(List<Edge>[] graph, long threshold){
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[graph.length+1];
		q.offer(src);
		visited[src] = true;

		while(!q.isEmpty()){
			int cur = q.poll();
			if(cur == dest){
				return true;
			}

			for(Edge next : graph[cur]){
				if(!visited[next.to] && next.weight >= threshold){
					q.offer(next.to);
					visited[next.to] = true;
				}
			}
		}
		return false;
	}


}
