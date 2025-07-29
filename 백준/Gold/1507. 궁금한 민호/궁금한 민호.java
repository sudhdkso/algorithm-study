import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[][] graph = new int[N][N];
		boolean[][] isNeeded = new boolean[N][N];

		for(int i=0;i<N;i++) Arrays.fill(isNeeded[i], true);

		for(int i=0;i<N;i++){
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++){
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int k=0;k<N;k++){
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(i==j || i == k || j == k) continue;
					if(graph[i][j] == graph[i][k] + graph[k][j]){
						isNeeded[i][j] = false;
					}
					else if(graph[i][j] > graph[i][k] + graph[k][j]){
						bw.write("-1 \n");
						bw.flush();
						bw.close();
						return;
					}
				}
			}
		}
		int answer =0;
		for(int i=0;i<N;i++){
			for(int j=i;j<N;j++){
				if(isNeeded[i][j]){
					answer += graph[i][j];
				}
			}
		}

		bw.write(answer+"\n");
		bw.flush();
		bw.close();
	}
}