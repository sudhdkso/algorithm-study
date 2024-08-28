import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		go(arr,M,0,new boolean[N]);
		System.out.println(sb.toString());
	}

	private static StringBuilder sb = new StringBuilder();
	private static Set<String> set = new HashSet<>();
	public static void go(int[] arr, int M, int depth, boolean[] visited){
		if(depth >= M){
			StringBuilder now = new StringBuilder();
			for(int i=0;i<visited.length;i++){
				if(!visited[i]) continue;
				now.append(arr[i]).append(" ");
			}

			if(!set.contains(now.toString())){
				set.add(now.toString());
				sb.append(now).append("\n");
			}
			return;
		}

		for(int i=depth;i<arr.length;i++){
			if(visited[i]) continue;
			visited[i] = true;
			go(arr, M, depth+1,visited);
			visited[i] = false;
		}
	}
}