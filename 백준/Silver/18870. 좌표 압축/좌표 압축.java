import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer> unique = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sortArr = Arrays.copyOf(arr,N);

        Arrays.sort(sortArr);

        unique = new ArrayList<>();

        for(int i=0;i<N;i++){
            if(i == 0 || sortArr[i-1] != sortArr[i]){
                unique.add(sortArr[i]);
            }
        }

        for(int i=0;i<N;i++){
            int count = binarySearch(arr[i]);
            bw.write(count+" ");
        }

        bw.flush();
    }

    private static int binarySearch(int target){

        int low = 0, high = unique.size()-1;

        while(low<=high){
            int mid = (low+high)/2;

            if(unique.get(mid) < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return low;
    }

}