import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		StringTokenizer t = new StringTokenizer(br.readLine()," ");

		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(t.nextToken());
		}

		int left = 0, right = N-1;
		int max = (right-left-1) * Math.min(arr[left], arr[right]);
		while(left <= right){
			max = Math.max(max, (right-left-1) * Math.min(arr[left], arr[right]));
			if(arr[left] >= arr[right]){
				right--;
			}
			else{
				left++;
			}
		}

		System.out.println(max);
	}
}