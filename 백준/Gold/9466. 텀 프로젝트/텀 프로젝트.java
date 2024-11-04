import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] want;
	private static boolean[] visited, isGroup;
	private static int n;
	private static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			n = Integer.parseInt(br.readLine());
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");


			visited = new boolean[n+1];
			want = new int[n+1];
			isGroup = new boolean[n+1];

			for (int i = 1; i <= n; i++) {
				want[i] = Integer.parseInt(st.nextToken());
				if(i == want[i]){
					isGroup[i] = true;
					answer++;
				}
			}

			for (int i = 1; i <= n; i++) {
				if (isGroup[i]) continue;
				dfs(i);
			}

			System.out.println((n-answer));
		}

	}

	private static void dfs(int cur){
		if(visited[cur]){
			return;
		}
		visited[cur] = true;
		int next = want[cur];

		if(!visited[next]){
			dfs(next);
		} else {
			if(!isGroup[next]){
				answer++;
				while(next != cur){
					answer++;
					next = want[next];
				}
			}
		}
		isGroup[cur] = true;

	}
}