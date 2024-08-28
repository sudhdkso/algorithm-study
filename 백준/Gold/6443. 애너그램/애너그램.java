import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		String[] str = new String[N];

		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
		}

		for (int i = 0; i < N; i++) {
			int[] count = setCharCount(str[i]);
			go(count, 0, str[i].length(), new int[str[i].length()]);
			printAnagram();
			set.clear();
		}
	}

	private static void printAnagram(){
		for(String s : set.stream().sorted().collect(Collectors.toList())){
			System.out.println(s);
		}
	}

	private static int[] setCharCount(String str) {
		int[] count = new int[27];

		for (char c : str.toCharArray()) {
			count[c - 'a']++;
		}
		return count;
	}

	private static Set<String> set = new HashSet<>();

	private static void go(int[] count, int depth, int M, int[] order) {
		if (depth >= M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<order.length;i++){
				sb.append((char)(order[i]+'a'));
			}
			set.add(sb.reverse().toString());
			return;
		}

		for (int i = 0; i < count.length; i++) {
			if (count[i] == 0)
				continue;
			count[i]--;
			order[depth] = i;
			go(count, depth+1 , M,order);
			count[i]++;
		}
	}
}