import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int x,y;
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static char FIRE = 'F', WALL = '#', EMPTY = '.';
	static int R,C;
	static Queue<Point> jihoon = new LinkedList<>();
	static Queue<Point> fire = new LinkedList<>();
	static boolean[][] visitedFire, visitedJihoon;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		visitedJihoon = new boolean[R][C];
		visitedFire = new boolean[R][C];

		for(int i=0;i<R;i++){
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<C;j++){
				if(map[i][j] == 'J'){
					jihoon.offer(new Point(i,j));
					visitedJihoon[i][j] = true;
				}
				if(map[i][j] == FIRE){
					fire.offer(new Point(i,j));
					visitedFire[i][j] = true;
				}
			}
		}
		int answer = 0;
		while (!jihoon.isEmpty()){
			spreadFire(map);
			answer++;
			if(moveJihoon(map)){
				bw.write(answer+"\n");
				bw.flush();
				bw.close();
				return;
			}
		}

		bw.write("IMPOSSIBLE\n");
		bw.flush();
		bw.close();
	}
	static int[] dx = {1,0,-1,0},dy = {0,1,0,-1};
	private static void spreadFire(char[][] map){
		int size = fire.size();
		while(size-- > 0){
			Point cur = fire.poll();
			for(int i=0;i<4;i++){
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
				if(!visitedFire[nx][ny] && map[nx][ny] != WALL && map[nx][ny] != FIRE){
					visitedFire[nx][ny] = true;
					map[nx][ny] = FIRE;
					fire.offer(new Point(nx, ny));
				}
			}
		}
	}

	private static boolean moveJihoon(char[][] map){
		int size = jihoon.size();

		while(size-- >0){
			Point cur = jihoon.poll();
			for(int i=0;i<4;i++){
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx < 0 || ny < 0 || nx >= R || ny >= C){
					return true;
				}

				if(!visitedJihoon[nx][ny] && map[nx][ny] == '.'){
					visitedJihoon[nx][ny] = true;
					jihoon.offer(new Point(nx, ny));
				}
			}
		}
		return false;
	}
}