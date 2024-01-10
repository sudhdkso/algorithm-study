import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        int count = times.length;
        Arrays.sort(times);
        
        long start = 0, end = (long)times[count-1] * n;

        while(start <= end){
            long mid = (start + end) / 2;
            long sum = caculateTimes(times, mid);
            if(sum  >= n){
                answer = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
            
        }
        return answer;
    }
    
    private long caculateTimes(int[] times, long total){   
        return Arrays.stream(times)
            .mapToLong(x -> total/x)
            .sum();
    }
}