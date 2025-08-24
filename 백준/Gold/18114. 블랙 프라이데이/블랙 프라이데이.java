import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		
		for(int i=0;i<N;i++){
			if(arr[i] == C){
				bw.write("1\n");
				bw.flush();
				bw.close();
				return;
			}
		}
		
		boolean result = searchTarget(-1, C);
		if(!result){
			for(int i=0;i<N;i++){
				boolean three = searchTarget(i, C-arr[i]);
				if(three){
					result = true;
					break;
				}
			}
		}

		int answer = result ? 1 : 0;
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
	}

	private static boolean searchTarget(int index, int target){
		int left = 0, right = N-1;
		while(left < right){
			if (left == index) {
				left++;
				continue;
			}
			if (right == index) {
				right--;
				continue;
			}
			int weight = arr[left] + arr[right];
			if(weight == target){
				return true;
			}
			if(weight <= target){
				left++;
			}
			else {
				right--;
			}
		}
		return false;
	}
}