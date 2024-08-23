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

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[] day = new int[N];

		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++){
			day[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 0, visited = 0;

		int answer = 0, count = 0;

		while(left<=right && right < N){
			while(X > 0 && right < N){
				visited += day[right++];
				X--;
			}

			if(visited > answer){
				answer = visited;
				count = 1;
			}
			else if(visited == answer){
				count++;
			}
			visited -= day[left++];
			X++;
		}
		if(answer == 0){
			System.out.println("SAD");
		}
		else{
			System.out.println(answer+"\n"+count);
		}
	}
}