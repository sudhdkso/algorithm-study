import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer> two = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] card = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i=0;i<N;i++){
            card[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(card);
        int M = Integer.parseInt(br.readLine());
        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine()," ");

        for(int i=0;i<M;i++){
            int target = Integer.parseInt(st.nextToken());
            bw.write((upperBound(card, target)-lowerBound(card, target))+" ");
        }
        bw.flush();
    }

    private static int upperBound(int[] arr, int target){
        int low = 0, high = arr.length;

        while(low < high){
            int mid = (low+high)/2;

            if(arr[mid] > target){
                high = mid;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }

    private static int lowerBound(int[] arr, int target){
        int low = 0, high = arr.length;

        while(low < high){
            int mid = (low+high)/2;

            if(arr[mid] >= target){
                high = mid;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }
}