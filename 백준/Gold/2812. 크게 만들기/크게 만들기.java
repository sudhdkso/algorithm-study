import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer s = new StringTokenizer(br.readLine()," ");

		int N = Integer.parseInt(s.nextToken());
		int K = Integer.parseInt(s.nextToken());
		String number = br.readLine();

		int MAX_LENGTH = number.length() - K;
		Stack<Integer> st = new Stack<>();

		for(String n : number.split("")){
			int now = Integer.parseInt(n);
			if(st.empty()){
				st.push(now);
			}
			else if(K > 0 && now > st.peek()){
				while(!st.empty() && K > 0 && now >st.peek()){
					st.pop();
					K--;
				}
				st.push(now);
			}
			else {
				st.push(now);
			}
		}
		StringBuilder sb = new StringBuilder();

		while(!st.empty()){
			sb.append(st.pop());
		}
		sb.reverse();
		if(sb.length() > MAX_LENGTH){
			bw.write(sb.substring(0, MAX_LENGTH));
		}
		else {
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}
}