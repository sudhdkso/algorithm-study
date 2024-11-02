import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] snow = new int[N];

		StringTokenizer s = new StringTokenizer(br.readLine()," ");

		for(int i=0;i<N;i++){
			snow[i] = Integer.parseInt(s.nextToken());
		}

		Arrays.sort(snow);
		int min = Integer.MAX_VALUE;
		for(int i=0;i<N;i++){
			for(int j=N-1;j>i;j--){
				int height = snow[i] + snow[j];
				min = Math.min(min, getMinDiff(snow, i+1, j-1, height));
				//System.out.println(i+" "+j+" "+height);
			}
		}

		System.out.println(min);
	}


	private static int getMinDiff(int[] snow, int left, int right, int height){
		int min = Integer.MAX_VALUE;
		while(left < right){
			int n = snow[left] + snow[right];
			min = Math.min(min, Math.abs(height-n));
			if(height >= n){
				left++;
			}
			else{
				right--;
			}
		}
		return min;
	}
}