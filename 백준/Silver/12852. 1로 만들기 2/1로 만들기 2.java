import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N+1];
		int[] pre = new int[N+1];

		dp[1] = 0;

		for(int i=2;i<=N;i++){
			dp[i] = dp[i-1] + 1;
			pre[i] = i-1;
			if(i%2 == 0 && dp[i] > dp[i/2]+1){
				dp[i] = dp[i/2]+1;
				pre[i] = i/2;
			}
			if(i%3 == 0 && dp[i] > dp[i/3]+1){
				dp[i] = dp[i/3]+1;
				pre[i] = i/3;
			}
		}
		int index = N;

		bw.write(dp[N]+"\n");
		bw.write(N+" ");
		while(pre[index] != 0){
			bw.write(pre[index]+" ");
			index = pre[index];
		}
		bw.flush();
    }


}