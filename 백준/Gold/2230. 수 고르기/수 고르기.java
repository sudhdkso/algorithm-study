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

		StringTokenizer t = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(t.nextToken());
		int M = Integer.parseInt(t.nextToken());

		int[] arr = new int[N];

		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int min = Integer.MAX_VALUE;
		int en = 0;
		for(int st = 0; st<N;st++){
			while(en < N && arr[en] - arr[st] < M) en++;
			if(en == N) break;
			min = Math.min(min, arr[en] - arr[st]);
		}

		bw.write(min+"\n");
		bw.flush();
    }
}