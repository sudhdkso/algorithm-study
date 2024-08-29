import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Point {
		int x, y, count;

		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}

		public Point(int x, int y) {
			this(x, y, 0);
		}

		@Override
		public String toString() {
			return "Point{" +
				"x=" + x +
				", y=" + y +
				", count=" + count +
				'}';
		}
	}

	private static List<Integer>[] rocks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		rocks = new List[T + 1];

		for (int i = 0; i <= T; i++) {
			rocks[i] = new ArrayList<Integer>();
		}

		int mx = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");

			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			rocks[y].add(x);
			mx = Math.max(mx, x);
		}

		for (int i = 0; i <= T; i++) {
			Collections.sort(rocks[i]);
		}

		System.out.println(bfs(mx, T));
	}

	private static int bfs(int mx, int T) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		boolean[][] visited = new boolean[mx+1][T + 1];
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.y == T) {
				return now.count;
			}

			for (int y = now.y - 2; y <= now.y + 2; y++) {
				if (y < 0 || y > T) {
					continue;
				}
				for (int x : rocks[y]) {
					if (Math.abs(now.x - x) <= 2  && !visited[x][y]) {
						q.offer(new Point(x, y, now.count + 1));
						visited[x][y] = true;

					}
				}
			}

		}
		return -1;
	}
}