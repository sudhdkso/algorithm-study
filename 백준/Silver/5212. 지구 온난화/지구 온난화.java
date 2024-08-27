import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static char SEA = '.', ISEL = 'X';
	private static Stack<Integer> removeRow = new Stack<>();
	private static Stack<Integer> removeCol = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		char[][] fmap = new char[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == SEA) {
					fmap[i][j] = SEA;
				} else {
					if (isGoDown(map, i, j)) {
						fmap[i][j] = SEA;
					}
					else {
						fmap[i][j] = ISEL;
					}
				}
			}
		}
		setRemoveableMark(map, fmap);
		printMap(fmap);
	}

	private static void printMap(char[][] map){
		for(int i=0;i<map.length;i++){
			if(removeRow.contains(i)) continue;
			for(int j=0;j<map[0].length;j++){
				if(removeCol.contains(j)) continue;
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void setRemoveableMark(char[][] map, char[][] fmap){

		for(int i=0;i<map.length;i++){
			if(!isRemovableRow(fmap, i)){
				break;
			}
			removeRow.push(i);
		}

		for(int i = map.length-1;i>=0;i--){
			if(!isRemovableRow(fmap,i)){
				break;
			}
			removeRow.push(i);
		}

		for(int i=0;i<map[0].length;i++){
			if(!isRemovableCol(fmap,i)){
				break;
			}
			removeCol.push(i);
		}

		for(int i=map[0].length-1;i>=0;i--){
			if(!isRemovableCol(fmap,i)){
				break;
			}
			removeCol.push(i);
		}
	}

	private static void setRemoveMarkRow(char[][] map, int row){
		for(int i=0;i<map[row].length;i++){
			map[row][i] = 'N';
		}
	}

	private static void setRemoveMarkCol(char[][] map, int col){
		for(int i=0;i<map.length;i++){
			map[i][col] = 'N';
		}
	}

	private static boolean isRemovableRow(char[][] map, int row){
		for(int i=0;i<map[row].length;i++){
			if(map[row][i] != SEA){
				return false;
			}
		}
		return true;
	}

	private static boolean isRemovableCol(char[][] map, int col){
		for(int i=0;i<map.length;i++){
			if(map[i][col] != SEA){
				return false;
			}
		}
		return true;
	}
    
	private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

	private static boolean isGoDown(char[][] map, int x, int y) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length){
				count++;
			}
			else if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
				if (map[nx][ny] == SEA) {
					count++;
				}
			}
		}
		return count >= 3;
	}

}