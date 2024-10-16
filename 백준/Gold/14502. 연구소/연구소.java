import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int AREA = 0, WALL = 1, BIRUS = 2;
	private static int answer = 0;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0,map, N, M);

		bw.write(String.valueOf(answer));
		bw.flush();
	}

	private static void dfs( int count, int[][] map, int N, int M) {
		if(count >= 3){
			int result = bfs(map);
			answer = Math.max(result, answer);
			return;
		}

		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(map[i][j] == AREA){
					map[i][j] = WALL;
					dfs(count+1, map, N,M);
					map[i][j] = AREA;
				}
			}
		}
	}
	private static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
	private static int bfs(int[][] map) {
		int N = map.length, M = map[0].length;
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		int birus = 0;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(map[i][j] == BIRUS){
					q.offer(new int[]{i,j});
					visited[i][j] = true;
				}
			}
		}
		while(!q.isEmpty()){
			int[] now = q.poll();
			for(int i=0;i<4;i++){
				int nx = now[0]+dx[i];
				int ny = now[1]+dy[i];
				if(nx >= 0 && ny >= 0 && nx < N && ny < M){
					if(map[nx][ny] == WALL) continue;
					if(!visited[nx][ny] && map[nx][ny] == 0){
						birus++;
						q.offer(new int[]{nx,ny});
						visited[nx][ny] = true;
					}
				}
			}
		}

		return countSafeArea(map)-birus;
	}

	private static int countSafeArea(int[][] map){
		int count = 0;
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				if(map[i][j] == AREA){
					count++;
				}
			}
		}
		return count;
	}
}