import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for(int i=0;i<N;i++){
			String s = br.readLine();
			int result = checkPalindromeType(s, 0, s.length()-1, 0);
			System.out.println(result);
		}


	}

	private static int checkPalindromeType(String s, int start, int end, int count){
		if(count >= 2){
			return count;
		}

		while(start <  end){
			if(s.charAt(start) == s.charAt(end)){
				start++;
				end--;
			}
			else {
				return Math.min(checkPalindromeType(s, start+1, end, count+1), checkPalindromeType(s, start,end-1,count+1));
			}
		}
		return count;
	}

}