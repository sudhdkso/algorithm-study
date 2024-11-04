import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] want;
	private static boolean[] visited, isSearched;
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
			isSearched = new boolean[n+1];

			for (int i = 1; i <= n; i++) {
				want[i] = Integer.parseInt(st.nextToken());
				if(i == want[i]){
					isSearched[i] = true;
					answer++;
				}
			}

			for (int i = 1; i <= n; i++) {
				if (isSearched[i]) continue;
				dfs(i);
			}

			System.out.println((n-answer));
		}

	}

	private static void dfs(int cur){
		if(isSearched[cur]) return;
		if(visited[cur]){
			isSearched[cur] = true;
 			answer++;
		}

		visited[cur] = true;
		dfs(want[cur]);
		isSearched[cur] = true;
		visited[cur] = false;
	}
}