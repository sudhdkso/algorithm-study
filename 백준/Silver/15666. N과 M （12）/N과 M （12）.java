import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
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
		go(arr, 0, M, new String[M]);

		result.forEach(
			System.out::println
		);
	}

	private static List<String> result = new ArrayList<>();

	private static void go(int[] arr, int depth, int M, String[] seq) {
		if (depth >= M) {
			String answer = String.join(" ", seq);

			if (isVaild(seq) && !result.contains(answer)) {
				result.add(answer);
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			seq[depth] = String.valueOf(arr[i]);
			go(arr,depth+1,M,seq);
		}

	}

	private static boolean isVaild(String[] arr){
		for(int i=1;i<arr.length;i++){
			int a1 = Integer.parseInt(arr[i-1]);
			int a2 = Integer.parseInt(arr[i]);
			if(a1 > a2){
				return false;
			}
		}
		return true;
	}
}