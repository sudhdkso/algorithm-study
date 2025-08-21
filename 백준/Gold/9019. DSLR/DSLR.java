import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			String result = bfs(A, B);

			sb.append(result).append("\n");

		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}
	static char[] d = {'D','S','L','R'};

	private static String bfs(int start, int target){
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[10001];
		int[] prev = new int[10001];
		char[] cmd = new char[10001];

		visited[start] = true;
		q.offer(start);

		while(!q.isEmpty()){
			int cur = q.poll();
			if(cur == target) {
				break;
			}


			for(char ch : d){
				int result = getResult(ch, cur);

				if (!visited[result]) {
					visited[result] = true;
					prev[result] = cur;
					cmd[result] = ch;
					q.offer(result);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		int now = target;

		while(now != start){
			sb.append(cmd[now]);
			now = prev[now];
		}

		return sb.reverse().toString();
	}

	private static int getResult(char ch, int cur) {
		if(ch == 'D'){
			return (cur *2)%10000;
		}
		else if(ch == 'S'){
			return cur <= 0 ? 9999: cur -1 ;
		}
		else if(ch == 'L'){
			return cur % 1000 * 10 + cur / 1000;
		}
		else if(ch == 'R'){
			return  cur % 10 * 1000 + cur / 10;
		}
		return cur;
	}
}