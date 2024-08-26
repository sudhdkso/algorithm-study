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
		int C = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int low = 0, high = arr[N-1];
		int answer = high;

		while(low <= high) {
			int mid = (low+high)/2;
			if(isCheck(arr, mid, C)){
				answer = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		System.out.println(answer);
	}

	private static boolean isCheck(int[] arr, int thresold, int C){
		int count = 1, n = 0;
		for(int i=1;i<arr.length; i++){
			if(arr[i] >= arr[n]+thresold){
				count++;
				n = i;
			}
			if(count >= C){
				return true;
			}
		}
		return false;
	}
}