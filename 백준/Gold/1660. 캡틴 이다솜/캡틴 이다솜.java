import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[] tetra = getTetra(N);

		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE-1);
		dp[0] = 0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=tetra.length;j++){
				if(tetra[j] == 0) break;
				if(i - tetra[j] >= 0){
					dp[i] = Math.min(dp[i], dp[i - tetra[j]]+1);
				}

			}

		}

		bw.write(dp[N]+"\n");
		bw.flush();
		bw.close();
	}

	private static int[] getTetra(int N){
		int[] tetra = new int[122];

		for(int i=1;i<=N;i++){
			int t = (i*(i+1)*(i+2))/6;
			if(t <= N){
				tetra[i] = t;
			}
			else break;
		}
		return tetra;
	}
}