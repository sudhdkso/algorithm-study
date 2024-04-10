import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static ArrayList<Integer> two = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0;i<N;i++){
            for(int j=i;j<N;j++){
                two.add(arr[i]+arr[j]);
            }
        }

        Collections.sort(two);
        int max = 0;
        for(int i=N-1;i>=0;i--){
            for(int j=0;j<i;j++){
                if(binarySearch(arr[i]-arr[j])){
                    max = Math.max(arr[i],max);
                    
                }
            }
        }
        bw.write(String.valueOf(max));
        bw.flush();
    }

    private static boolean binarySearch(int target){

        int low = 0, high = two.size()-1;

        while(low<=high){
            int mid = (low+high)/2;
            if(two.get(mid) == target){
                return true;
            }
            else if(two.get(mid) < target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return false;
    }

}