import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] cheeze = new int[n][m];
		int answer = 0;
		int totalCheeze = 0;
		for(int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<m;j++){
				cheeze[i][j] = Integer.parseInt(st.nextToken());
				totalCheeze += cheeze[i][j] == 1 ? 1 : 0;
			}
		}

		int lastCheezeCount = 0;

		while(totalCheeze != 0){
			meltCheeze.clear();
			bfs(cheeze, n, m);
			if(!meltCheeze.isEmpty()){
				if(totalCheeze - meltCheeze.size() ==  0){
					lastCheezeCount = totalCheeze;
				}
				totalCheeze -= meltCheeze.size();
				answer++;
				setMeltCheeze(cheeze);
			}
			if(totalCheeze == 0){
				break;
			}
		}
		System.out.println(answer);
		System.out.println(lastCheezeCount);
	}


	private static void setMeltCheeze(int[][] map){
		while(!meltCheeze.isEmpty()){
			Point now = meltCheeze.pop();
			map[now.x][now.y] = 0;
		}
	}

	private static Stack<Point> meltCheeze = new Stack<>();
	private static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
	private static void bfs(int[][] map, int n, int m){
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];

		visited[0][0] = true;
		q.offer(new Point(0,0));

		while(!q.isEmpty()){
			Point now = q.poll();

			for(int i=0;i<4;i++){
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if(nx >= 0 && ny >= 0 && nx < n && ny < m){
					if(visited[nx][ny]) continue;
					visited[nx][ny] = true;
					if(map[nx][ny] == 1){
						meltCheeze.push(new Point(nx, ny));
					}
					else{
						q.offer(new Point(nx,ny));
					}
				}
			}
		}
	}
}