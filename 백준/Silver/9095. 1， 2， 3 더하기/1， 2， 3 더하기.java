import java.io.*;

public class Main {
    public static int answer = 0;
    public static void go(int count, int sum, int n){
        if(sum > n) return;
        if(sum == n) {
            answer++;
            return;
        }
        go(count++,sum+1,n);
        go(count++,sum+2,n);
        go(count++,sum+3,n);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            answer = 0;
            int n = Integer.parseInt(br.readLine());
            go(0,0,n);
            bw.write(String.valueOf(answer) + "\n");
            bw.flush();
        }
    }
}
