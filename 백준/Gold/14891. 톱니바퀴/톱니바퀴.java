import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] wheels = new int[4][8];
		int[] startIndex = new int[4];

		for (int i = 0; i < 4; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < input.length; j++) {
				wheels[i][j] = Integer.parseInt(input[j]);
			}
		}

		int K = Integer.parseInt(br.readLine());

		while (K-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken()) - 1;
			int f = Integer.parseInt(st.nextToken());

			int[] dir = new int[4];
			dir[num] = f;
			//왼쪽으로 전파
			for (int left = num - 1; left >= 0; left--) {
				int leftTooth = wheels[left+1][(startIndex[left+1]+6)%8];
				int rightTooth = wheels[left][(startIndex[left]+2)%8];

				if (leftTooth != rightTooth)
					dir[left] = -dir[left + 1];
				else
					break;
			}

			for (int right = num + 1; right < 4; right++) {
				int leftTooth = wheels[right-1][(startIndex[right-1]+2)%8];
				int rightTooth = wheels[right][(startIndex[right]+6)%8];

				if (leftTooth != rightTooth)
					dir[right] = -dir[right - 1];
				else
					break;
			}

			for (int i = 0; i < 4; i++) {
				if (dir[i] == 0)
					continue;
				else if (dir[i] > 0)
					startIndex[i] = advance(startIndex[i]);
				else
					startIndex[i] = reverse(startIndex[i]);
			}
		}
		int score = countScore(wheels, startIndex);

		bw.write(score + "\n");
		bw.flush();
		bw.close();
	}

	private static int countScore(int[][] wheels, int[] startIndex) {

		int score = 0;
		if (wheels[0][startIndex[0]] == 1)
			score += 1;
		if (wheels[1][startIndex[1]] == 1)
			score += 2;
		if (wheels[2][startIndex[2]] == 1)
			score += 4;
		if (wheels[3][startIndex[3]] == 1)
			score += 8;
		return score;
	}

	//ㅅㅣ계
	private static int advance(int index) {
		return (index+7)%8;
	}

	//반시계
	private static int reverse(int index) {
		return (index+1)%8;
	}
}