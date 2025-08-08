import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int line, index, time;
		public Node(int line, int index, int time){
			this.line = line;
			this.index = index;
			this.time = time;
		}
	}
	static int N,K;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // Map의 크기
		K = Integer.parseInt(st.nextToken());
		map = new char[3][N];

		map[1] = br.readLine().toCharArray();
		map[2] = br.readLine().toCharArray();
		int answer = bfs() ? 1: 0;

		bw.write(answer+"\n");
		bw.flush();
		bw.close();
	}


	private static boolean bfs(){
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[3][N+K];
		q.offer(new Node(1, 0, 0));
		visited[1][0] = true;

		while(!q.isEmpty()){
			Node cur = q.poll();
			int index = cur.index, nowLine = cur.line, time = cur.time;
			int nextLine = nowLine == 1 ? 2 : 1;
			if(cur.index >= N) return true;

			if(index+K >= N || index+1>=N){
				return true;
			}
			if(index-1 >= time+1 && !visited[nowLine][index-1] && map[nowLine][index-1] == '1'){
				q.offer(new Node(nowLine, index-1, time+1));
				visited[nowLine][index-1] = true;
			}
			if(index+1 < N && !visited[nowLine][index+1] && map[nowLine][index+1] == '1'){
				q.offer(new Node(nowLine, index+1, time+1));
				visited[nowLine][index+1] = true;
			}
			if(index+K <N && !visited[nextLine][index+K] && map[nextLine][index+K] == '1'){
				q.offer(new Node(nextLine, index+K, time+1));
				visited[nextLine][index+K] = true;
			}
		}
		return false;
	}
}