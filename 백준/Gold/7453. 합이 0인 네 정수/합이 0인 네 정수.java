import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		long[] A = new long[N];
		long[] B = new long[N];
		long[] C = new long[N];
		long[] D = new long[N];

		for(int i=0;i<N;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[i] = Long.parseLong(st.nextToken()); //A
			B[i] = Long.parseLong(st.nextToken()); //B
			C[i] = Long.parseLong(st.nextToken()); //C;
			D[i] = Long.parseLong(st.nextToken()); //D;
		}

		//A+B C+D
		long[] AB = new long[N*N];

		HashMap<Long, Integer> CD = new HashMap<>(N * N);
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				AB[i*N+j] = A[i]+B[j];
				long sum = C[i]+D[j];
				CD.put(sum,CD.getOrDefault(sum,0)+1);
			}
		}

		long count = 0;
		for(long sum : AB){
			count += CD.getOrDefault(-sum, 0);
		}

		bw.write(count+"\n");
		bw.flush();
		bw.close();
	}
}