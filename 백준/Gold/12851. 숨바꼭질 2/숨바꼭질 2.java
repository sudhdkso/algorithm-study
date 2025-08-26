import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Position{
		int x, time;

		public Position(int x, int time){
			this.x = x;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st= new StringTokenizer(br.readLine()," ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] result = bfs(N, K);
		bw.write(result[0]+"\n"+result[1]+"\n");
		bw.flush();
		bw.close();
	}

	private static int[] bfs(int start, int dst){
		Queue<Position> q = new LinkedList<>();
		int[] dist = new int[100_001];
		Arrays.fill(dist, -1);

		q.offer(new Position(start, 0));
		dist[start] = 0;

		int min = Integer.MAX_VALUE, count = 0;
		while(!q.isEmpty()){
			Position p = q.poll();
			int cur = p.x;
			if(cur == dst){
				if(min == p.time) count++;
				else if(min > p.time){
					min = p.time;
					count = 1;
				}
			}

			for(int n : new int[]{cur+1,cur-1,cur*2}){
				if(n < 0 || n > 100_000) continue;
				if(dist[n] == -1 || dist[n] == dist[cur] + 1){
					dist[n] = dist[cur]+1;
					q.offer(new Position(n, p.time+1));
				}
			}
		}
		return new int[]{min, count};
	}
}