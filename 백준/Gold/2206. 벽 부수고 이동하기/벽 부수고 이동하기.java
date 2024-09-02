import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Point {
		int x, y;
		boolean used;

		public Point(int x, int y, boolean used) {
			this.x = x;
			this.y = y;
			this.used = used;
		}

		public Point(int x, int y) {
			this(x, y, false);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		System.out.println(bfs(map));
	}

	private static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

	private static int bfs(int[][] map) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));

		int N = map.length, M = map[0].length;

		int[][][] dist = new int[N][M][2];

		for (int i = 0; i < N; i++) {
			for(int j=0;j<M;j++){
				Arrays.fill(dist[i][j],Integer.MAX_VALUE-1);
			}
		}
		dist[0][0][0] = dist[0][0][1] = 1;

		while (!q.isEmpty()) {
			Point now = q.poll();
			int u = now.used ? 1 : 0;

			if(now.x ==N-1 && now.y == M-1){
				return dist[now.x][now.y][u];
			}
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (map[nx][ny] == 1 && now.used)
					continue;
				if (map[nx][ny] == 0 && dist[nx][ny][u] > dist[now.x][now.y][u] + 1) {
					q.offer(new Point(nx, ny, now.used));
					dist[nx][ny][u] = dist[now.x][now.y][u] + 1;
				}
				if (map[nx][ny] == 1 && !now.used && dist[nx][ny][1] > dist[now.x][now.y][0] + 1) {
					q.offer(new Point(nx, ny, true));
					dist[nx][ny][1] = dist[now.x][now.y][0] + 1;
				}

			}
		}
		return -1;
	}
}