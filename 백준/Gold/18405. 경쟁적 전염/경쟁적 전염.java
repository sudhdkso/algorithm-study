import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine()," ");

		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		int s = 0;
		while(S > s){
			for(int i=1;i<=K;i++){
				bfs(map,i);
			}
			
			if(map[X-1][Y-1] > 0){
				break;
			}
			s++;
		}

		System.out.println(map[X-1][Y-1]);
	}

	private static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

	private static void bfs(int[][] map, int birus){
		Queue<int[]> q = new LinkedList<>();

		int N = map.length;
		boolean[][] visited = new boolean[N][N];

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j] == birus){
					q.offer(new int[]{i,j});
					visited[i][j] = true;
				}
			}
		}

		while(!q.isEmpty()){
			int[] now = q.poll();

			for(int i=0;i<4;i++){
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if(nx >=0 && ny >= 0 && nx < N && ny < N){
					if(!visited[nx][ny] && map[nx][ny] == 0){
						visited[nx][ny] = true;
						map[nx][ny] = birus;
					}
				}
			}
		}
	}
}