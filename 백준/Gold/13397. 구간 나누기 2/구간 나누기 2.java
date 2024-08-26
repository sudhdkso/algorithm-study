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

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		int low = 0, high = max;
		while (low < high) {
			int mid = (low+high)/2;
			if(isCheck(arr,M,mid)){
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(high);
	}

	private static boolean isCheck(int[] arr, int M, int threshold){

		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		int count = 1;
		for(int i=0;i<arr.length;i++){
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
			//max와 min의 차이가 threshold보다 크면 구간 이동
			if(max-min > threshold){
				min = arr[i];
				max = arr[i];
				count++;
			}
		}
		return M >= count;
	}

}