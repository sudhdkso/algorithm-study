import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static class Point{
		int x,y;
		public Point(int x,int y){
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];

		for(int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<m;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		while(true){
			boolean[][] visited = new boolean[n][m];
			int count = 0;
			meltIced(map);
			for(int i=0;i<n;i++){
				for(int j=0;j<m;j++){
					if(!visited[i][j] && map[i][j] > 0){
						meltPoints.clear();
						bfs(map, visited, i, j);
						count++;
					}
				}
			}

			if(count >= 2){
				System.out.println(answer);
				break;
			}
			if(count == 0){
				System.out.println(0);
				break;
			}
			answer++;
		}
	}

	private static Stack<Point> meltPoints = new Stack<>();
	private static int[] dx = {1,0,-1,0},dy = {0,1,0,-1};

	private static void meltIced(int[][] map){
		while(!meltPoints.isEmpty()){
			Point now = meltPoints.pop();
			if(map[now.x][now.y] == 0) continue;
			map[now.x][now.y]--;
		}
	}
	private static void bfs(int[][] map, boolean[][] visited, int sx, int sy){

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(sx, sy));
		int n = map.length, m = map[0].length;
        visited[sx][sy] = true;
        
		while(!q.isEmpty()){
			Point now = q.poll();

			for(int i=0;i<4;i++){
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if(nx >= 0 && ny >= 0 && nx < n && ny < m){
					if(visited[nx][ny]) continue;
					if(map[nx][ny] == 0){
						meltPoints.push(new Point(now.x, now.y));
					}
					if(map[nx][ny] > 0){
						visited[nx][ny] = true;
						q.offer(new Point(nx,ny));
					}
				}
			}
		}
	}
}