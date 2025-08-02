import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long[] preSum = getPrefixSum(arr);

		int[] count = new int[M];

		for (long num : preSum) {
			count[(int)(num % M)]++;
		}

		long answer = 0;
		for (int i : count) {
			answer += (long)i * (i - 1) / 2;
		}
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
	}

	private static long[] getPrefixSum(int[] arr){
		long[] pre = new long[arr.length+1];
		pre[0] = 0;

		for(int i=1;i<=arr.length;i++){
			pre[i] = pre[i-1] + arr[i-1];
		}

		return  pre;
	}
}