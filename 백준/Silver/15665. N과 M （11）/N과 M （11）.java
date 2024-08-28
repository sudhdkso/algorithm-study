import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static Set<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		go(arr, 0, M, new int[M]);
		System.out.println(sb);
	}
	
	private static void go(int[] arr, int depth, int M, int[] sequence) {
		if (depth >= M) {
			StringBuilder now = new StringBuilder();

			for(int i=0;i < sequence.length;i++){
				now.append(sequence[i]+" ");
			}

			if(!set.contains(now.toString())){
				set.add(now.toString());
				sb.append(now).append("\n");
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			sequence[depth] = arr[i];
			go(arr,depth+1,M,sequence);
		}
	}
}