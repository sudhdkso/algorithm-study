import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		while (true) {
			boolean flag = false;
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j])
						continue;
					int result = bfs(i, j, arr, visited, L, R);
					if(result != arr[i][j]){
						flag = true;
					}
					movePeople(result, arr);
				}
			}
			if(!flag){
				break;
			}
			answer++;
		}

		System.out.println(answer);

	}

	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	private static Stack<int[]> s = new Stack<>();

	private static int bfs(int x, int y, int[][] arr, boolean[][] visited, int L, int R) {
		Queue<int[]> q = new LinkedList<>();
		int N = arr.length;
		q.offer(new int[] {x, y});
		s.push(new int[] {x, y});

		int count = arr[x][y];
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					if (!visited[nx][ny] && isPossible(Math.abs(arr[now[0]][now[1]] - arr[nx][ny]), L, R)) {
						q.offer(new int[] {nx, ny});
						visited[nx][ny] = true;
						s.push(new int[] {nx, ny});
						count += arr[nx][ny];
					}
				}
			}
		}
		return count;
	}

	private static void movePeople(int people, int[][] arr){
		int num = people/s.size();
		while(!s.isEmpty()){
			int[] now = s.pop();
			arr[now[0]][now[1]] = num;
		}
	}

	private static boolean isPossible(int diff, int L, int R) {
		return diff >= L && diff <= R;
	}
}