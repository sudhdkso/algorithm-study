import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static class Node implements Comparable<Node>{
		int index, value;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			if(o.value == value){
				if(index > o.index){
					return 1;
				}
				else return -1;
			}
			else if(o.value > value){
				return 1;
			}
			else{
				return -1;
			}
		}
	}

	private static List<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		list = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}


		for(int i=1;i<=N;i++){
			Collections.sort(list[i]);
		}

		int answer = 0;
		for(int i=1;i<=N;i++){
			answer = Math.max(answer, dfs(i, 0, new boolean[N+1]));
		}

		System.out.println(answer);
	}


	private static int dfs(int now, int sum, boolean[] visited){
		int max = sum;
		visited[now] = true;

		for(Node next : list[now]){
			if(!visited[next.index]){
				visited[next.index] = true;
				int dist = dfs(next.index, sum+next.value, visited);
				max = Math.max(max, dist);
				visited[next.index] = false;
			}
		}
		return max;
	}
}