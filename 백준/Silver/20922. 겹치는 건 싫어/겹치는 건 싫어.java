import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] a = new int[N];

		st = new StringTokenizer(br.readLine()," ");

		for(int i=0;i<N;i++){
			a[i] = Integer.parseInt(st.nextToken());
		}

		Map<Integer,Integer> map = new HashMap<>();

		int left = 0, right = 0;
		int answer = 0;

		while(left <= right && left < N && right < N) {

			while(right < N){
				if(map.getOrDefault(a[right],0) >= K){
					break;
				}
				map.put(a[right], map.getOrDefault(a[right],0)+1);
				right++;

			}
			answer = Math.max(answer, right-left);
			map.put(a[left], map.get(a[left])-1);
			left++;

		}

		System.out.println(answer+"\n");
	}
}