import java.util.Arrays;

class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] arr = calcPrefixSum(n);
        int left = 0, right = 0;
        
        while(left <= right && right <= n){
            int num = arr[right] - arr[left];

            if(num == n){
                answer++;
            }
            if(num <= n){
                right++;
            }
            else if(num > n){
                left++;
            }
            
        }
        
        return answer;
    }
    
    private static int[] calcPrefixSum(int n){
        int[] arr = new int[n+1];
        
        for(int i=1;i<=n;i++){
            arr[i] = arr[i-1]+i;
        }
        return arr;
    }
}