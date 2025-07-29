import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = bfs(N,K);
		int index = K;
		StringBuilder sb = new StringBuilder();
		int count = 0;
		sb.append(K+" ");
		while(index != N){
			sb.append(arr[index]+" ");
			index = arr[index];
			count++;
		}

		int[] answer = new int[count+1];
		bw.write(count+"\n");
		for(String s : sb.toString().split(" ")){
			answer[count--] = Integer.parseInt(s);
		}

		for(int num : answer){
			bw.write(num+" ");
		}

		bw.flush();
		bw.close();
	}

	private static int[] bfs(int N, int K){
		boolean[] visited = new boolean[100_001];
		int[] prev = new int[100_001];
		Queue<Integer> q = new LinkedList<>();

		visited[N] = true;
		q.offer(N);

		while(!q.isEmpty()){
			int now = q.poll();
			if(now == K) return prev;

			for(int next : new int[]{now-1, now+1, now*2}){
				if(next < 0 || next > 100_000) continue;
				if(!visited[next]){
					visited[next] = true;
					prev[next] = now;
					q.offer(next);
				}
			}
		}
		return prev;
	}
}