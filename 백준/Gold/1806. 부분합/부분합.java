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

		StringTokenizer t = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(t.nextToken());
		int S = Integer.parseInt(t.nextToken());

		int[] arr = new int[N];
		t = new StringTokenizer(br.readLine()," ");

		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(t.nextToken());
		}

		int min = Integer.MAX_VALUE;
		int en = 0;
		int sum = arr[0];
		for(int st = 0; st<N;st++){
			while(en < N && sum < S){
				en++;
				if(en != N) sum += arr[en];
			}
			if(en == N) break;
			min = Math.min(min, en - st + 1);
			sum -= arr[st];
		}
		min = min == Integer.MAX_VALUE ? 0 : min;
		bw.write(min+"\n");
		bw.flush();
    }
}