class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        
        int maxDiff = 0;
        
        for(int i=0;i<diffs.length;i++){
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        
        int low = 1, high = maxDiff;
        
        while(low <= high) {
            int mid = (low+high)/2;
            if(check(diffs, times, mid, limit)){
                high = mid - 1;
                answer = Math.min(answer, mid);
            }
            else {
                low = mid + 1;
            }
        }
        return answer;
    }
    
    private static boolean check (int[] diffs, int[] times, int level, long limit){
        long time = 0;
        
        for(int i=0;i<diffs.length;i++){
            if(diffs[i] <= level){
                time += (long)times[i];
            }
            else{
                int failCount = diffs[i]-level;
                time += (long)((failCount*times[i]) + times[i]);
                if(i > 0){
                    time += (long)(times[i-1]*failCount);
                }
            }
            
        }
        return  limit >= time;
    }
    
}