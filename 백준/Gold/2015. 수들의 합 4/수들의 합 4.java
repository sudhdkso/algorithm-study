import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine()," ");
		HashMap<Long, Integer> map = new HashMap<>();
		map.put(0L, 1);

		long sum = 0, count = 0;
		for(int i=0;i<N;i++){
			sum += Integer.parseInt(st.nextToken());
			count += map.getOrDefault(sum - K, 0);
			map.put(sum, map.getOrDefault(sum, 0 )+1);

		}


		bw.write(count+"\n");
		bw.flush();
		bw.close();

	}
}