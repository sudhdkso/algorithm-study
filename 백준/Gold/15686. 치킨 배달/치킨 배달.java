import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static int HOUSE = 1, CHICKEN = 2;
	private static List<int[]> houses = new ArrayList<>();
	private static List<int[]> chickens = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == HOUSE){
					houses.add(new int[]{i,j});
				}
				if(map[i][j] == CHICKEN){
					chickens.add(new int[]{i,j});
				}
			}
		}
		int result = dfs(0, N, M, new boolean[chickens.size()], new Stack<>());
		System.out.println(result);
	}

	private static int dfs(int index, int N, int M, boolean[]visited, Stack<int[]> s){
		if(s.size() >= M){
			return calcMinChickenDist(new ArrayList<>(s));
		}

		int min = Integer.MAX_VALUE;
		for(int i=index;i<chickens.size();i++){
			if(!visited[i]){
				visited[i] = true;
				s.push(Arrays.copyOf(chickens.get(i), 2));
				min = Math.min(min, dfs(i+1,N,M, visited, s));
				s.pop();
				visited[i] = false;
			}
		}
		return min;
	}

	private static int calcDist(int[] a, int[] b){
		return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
	}

	private static int calcMinChickenDist(List<int[]> list){
		int sum = 0;
		for(int[] house: houses){
			int min = Integer.MAX_VALUE;
			for(int[] chicken : list){
				min = Math.min(min, calcDist(house, chicken));
			}
			sum += min;
		}
		return sum;
	}

}