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

	static char WATER = '*', STONE = 'X', START = 'S', DST = 'D';
	static int R,C;
	static 	Point src, dst;
	static boolean[][] visitedWater, visitedHedgehog;
	static Queue<Point> water = new LinkedList<>();
	static Queue<Point> hedgehog = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		visitedHedgehog = new boolean[R][C];
		visitedWater = new boolean[R][C];

		for(int i=0;i<R;i++){
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<C;j++){
				if(map[i][j] == START){
					hedgehog.offer(new Point(i,j));
					visitedHedgehog[i][j] = true;
				}
				if(map[i][j] == DST){
					dst = new Point(i,j);
				}
				if(map[i][j] == WATER){
					water.offer(new Point(i,j));
					visitedWater[i][j] = true;
				}
			}
		}
		int answer = 0;
		while(!hedgehog.isEmpty()){
			spreadWater(map);
			boolean isEnter = moveHedgehog(map);
			if(isEnter){
				bw.write(answer+"\n");
				bw.flush();
				bw.close();
				return;
			}
			answer++;
		}
		bw.write("KAKTUS\n");
		bw.flush();
		bw.close();

	}
	static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

	private static void spreadWater(char[][] map){
		int size = water.size();

		while(size-- > 0){
			if(water.isEmpty()) break;
			Point cur = water.poll();
			for(int i=0;i<4;i++){
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx < 0 || ny <0 || nx >= R || ny >= C) continue;
				if(!visitedWater[nx][ny] && map[nx][ny] == '.'){
					map[nx][ny] = WATER;
					water.offer(new Point(nx,ny));
					visitedWater[nx][ny] = true;
				}
			}
		}
	}

	private static boolean moveHedgehog(char[][] map){
		int size = hedgehog.size();

		while(size-- > 0){
			if(hedgehog.isEmpty()) break;
			Point cur = hedgehog.poll();
			if(cur.x == dst.x && cur.y == dst.y) return true;
			for(int i=0;i<4;i++){
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
				if(!visitedHedgehog[nx][ny] && map[nx][ny] != WATER && map[nx][ny] != STONE){
					hedgehog.offer(new Point(nx,ny));
					visitedHedgehog[nx][ny] = true;
				}
			}
		}
		return false;
	}
}