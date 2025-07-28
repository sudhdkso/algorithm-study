import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] history;
	static boolean[][] isBefore, isAfter;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		history = new List[N+1];
		isBefore = new boolean[N+1][N+1];
		isAfter = new boolean[N+1][N+1];

		for(int i=1;i<=N;i++){
			history[i] = new ArrayList<>();
		}

		for(int i=0;i<K;i++){
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			history[A].add(B);
		}

		for(int i=1;i<=N;i++){
			bfs(i);
		}

		int S = Integer.parseInt(br.readLine());

		while(S-- > 0){
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(!isBefore[a][b] && !isAfter[a][b]){
				bw.write("0 \n");
			}
			else if(isBefore[a][b]){
				bw.write("-1 \n");
			}
			else {
				bw.write("1 \n");
			}
		}


		bw.flush();
		bw.close();
	}

	private static void bfs(int start){
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;

		while(!q.isEmpty()){
			int now = q.poll();

			for(int next : history[now]){
				if(!visited[next]){
					q.offer(next);
					visited[next] = true;
					isBefore[start][next] = true;
					isAfter[next][start] = true;
				}
			}
		}
	}

}