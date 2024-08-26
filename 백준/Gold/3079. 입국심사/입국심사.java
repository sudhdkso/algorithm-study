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

		int[] arr =new int[N];

		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int min = arr[0];

		long low = 0, high = (long)min*(long)M;
		long answer = high;
		while(low <= high) {
			long mid = (low+high)/2;
			if(isCheck(arr,mid, M)){
				high = mid-1;
				answer = Math.min(answer, mid);
			}
			else{
				low = mid + 1;
			}
		}

		System.out.println(answer);
	}

	private static boolean isCheck(int[] arr, long thresold, int M){
		int count = 0;
		for(int i=0;i<arr.length;i++){
			count += thresold/arr[i];
			if(count >= M) {
				return true;
			}
		}
		return false;
	}
}