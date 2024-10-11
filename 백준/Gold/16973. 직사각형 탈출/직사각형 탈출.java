import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Rectangle {
		Point lt, rt, lb, rb;
		int count;

		public Rectangle(int x, int y, int r, int c, int count) {
			lt = new Point(x, y);
			rt = new Point(x, y + c - 1);
			lb = new Point(x + r - 1, c);
			rb = new Point(x + r - 1, y + c - 1);
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int sx = Integer.parseInt(st.nextToken()) - 1;
		int sy = Integer.parseInt(st.nextToken()) - 1;

		int fx = Integer.parseInt(st.nextToken()) - 1;
		int fy = Integer.parseInt(st.nextToken()) - 1;

		System.out.println(bfs(map, r, c, sx, sy, fx, fy));
	}

	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

	public static int bfs(int[][] map, int r, int c, int sx, int sy, int fx, int fy) {
		Queue<Rectangle> q = new LinkedList<>();

		q.offer(new Rectangle(sx, sy, r, c, 0));
		int n = map.length;
		int m = map[0].length;

		boolean[][] visited = new boolean[n][m];

		visited[sx][sy] = true;
		while (!q.isEmpty()) {
			Rectangle now = q.poll();

			if (now.lt.x == fx && now.lt.y == fy) {
				return now.count;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.lt.x + dx[i];
				int ny = now.lt.y + dy[i];

				int ex = now.rb.x + dx[i];
				int ey = now.rb.y + dy[i];
				if (nx >= 0 && ny >= 0 && ex < n && ey < m) {
					if (visited[nx][ny])
						continue;
					Rectangle next = new Rectangle(nx, ny, r, c, now.count + 1);
					if (canMove(map, next)) {
						q.offer(next);
						visited[nx][ny] = true;
					}
				}
			}

		}
		return -1;
	}

	private static boolean canMove(int[][] map, Rectangle rec) {
		for (int i = rec.lt.x; i <= rec.rb.x; i++) {
			for (int j = rec.lt.y; j <= rec.rb.y; j++) {
				if (map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
}