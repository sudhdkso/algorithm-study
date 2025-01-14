import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int low = 0, high = distance;
        
        int[] diff = new int[rocks.length+1];
        
        Arrays.sort(rocks);
        
        diff[0] = rocks[0];
        diff[diff.length-1] = distance - rocks[rocks.length-1];
        
        for(int i=1;i<rocks.length;i++){
            diff[i] = rocks[i] - rocks[i-1];    
        }
        while(low <= high){
            int mid = (low+high)/2;
            if(check(mid, diff, n)){
                low = mid + 1;
            }
            else {
                high = mid-1;
            }
        }
        return low-1;
    }
    
    private static boolean check(int target, int[] rocks, int n){
        int sum = 0, remove = 0;
        for(int i=0;i<rocks.length;i++){
            sum += rocks[i];
            if(sum < target){
                remove++;
            }
            else{
                sum = 0;
            }
        }
        return remove <= n;
    }
    
    
}