import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Main {
	private static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());
			list[num].add(i);
		}
		for (int i = 1; i <= N; i++) {
			dfs(i, i, new boolean[N + 1]);
		}
		System.out.println(result.size());
		Collections.sort(result);
		result.stream()
			.forEach(System.out::println);

	}

	private static List<Integer> result = new ArrayList<>();

	private static void dfs(int now, int start, boolean[] visited) {
		visited[now] = true;

		for(int next : list[now]){
			if(next == start){
				result.add(start);
				return;
			}
			if(visited[next]) continue;
			visited[next] = true;
			dfs(next, start,visited);
			visited[next] = false;
		}

	}

	private static boolean isAggregate(Stack<Integer> s1, Stack<Integer> s2) {
		Collections.sort(s1);
		Collections.sort(s2);

		while (!s1.isEmpty()) {
			int index = s1.pop();
			int value = s2.pop();
			if (index != value) {
				return false;
			}
		}
		return true;
	}
}