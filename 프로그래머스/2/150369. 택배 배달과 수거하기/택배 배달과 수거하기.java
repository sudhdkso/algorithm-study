class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        for(int i=n-1;i>=0;) {
            if(deliveries[i] == 0 && pickups[i] == 0) {
                i--;
                continue;
            }
            //n-1번째 집을 방문할 때 실어야 하는 택배의 수 계산
            calculateBoxCapacity(cap, i, deliveries);
           calculateBoxCapacity(cap, i, pickups);
            answer += (i+1)*2;
        }
        return answer;
    }
    
    private static void calculateBoxCapacity(int cap, int visitedIndex, int[] arr) {
        int count = 0;
        for(int i=visitedIndex;i>=0;i--){
            if(arr[i] ==0) continue;
            if(cap > count+arr[i]) {
                count += arr[i];
                arr[i] = 0;
            } else {
                arr[i] -= (cap-count);
                return;
            }
        }
        return;
    }
}