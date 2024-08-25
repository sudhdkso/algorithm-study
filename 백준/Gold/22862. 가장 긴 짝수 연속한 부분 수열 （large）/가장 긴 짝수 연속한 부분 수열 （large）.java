import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] S = new int[N];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}

		int right = 0, answer = 0, count = 0;
		for (int left = 0; left < N; left++) {
			while(right < N && (S[right]%2 == 0 || K > count)){
				if(S[right]%2 != 0){
					count++;
				}
				right++;
			}
			answer = Math.max(answer, right-left-count);
			if(right == N) break;

			if(S[left]%2 != 0) {
				count--;
			}
		}
		System.out.println(answer);
	}
}