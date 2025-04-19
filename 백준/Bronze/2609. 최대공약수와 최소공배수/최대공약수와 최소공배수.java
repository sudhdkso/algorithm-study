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

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		if(a < b ){
			int temp = b;
			b = a;
			a = temp;
		}

		bw.write(gcd(a,b)+"\n");
		bw.write(lcd(a,b)+"\n");
		bw.flush();

	}

	public static int gcd(int a, int b){
		if(b == 0) return a;
		return gcd(b, a % b);
	}

	public static int lcd(int a, int b){
		return (a*b)/gcd(a,b);
	}
}
