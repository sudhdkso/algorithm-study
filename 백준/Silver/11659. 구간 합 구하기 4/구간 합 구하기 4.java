import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static int[] calcPrefix(int[] arr){
        int len = arr.length;
        int[] temp = new int[len+1];
        temp[0] = 0;
        for(int i=1;i<len+1;i++){
            temp[i] = temp[i-1] + arr[i-1];
        }
        return temp;
    }
    public static void main (String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] prefixSum = calcPrefix(arr);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            sb.append(prefixSum[b]-prefixSum[a-1]).append("\n");
        }
        bw.write(sb.toString()+"\n");
        bw.flush();
    }
}