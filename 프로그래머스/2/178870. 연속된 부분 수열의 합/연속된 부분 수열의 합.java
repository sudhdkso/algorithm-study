class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int min = 1_000_0001;
        int left = 0, right = 0;
        int sum = sequence[left];
        while(left <= right && right < sequence.length){
            if(sum == k){
                int len = right - left + 1;
                if(len < min){
                    min = len;
                    answer[0] = left;
                    answer[1] = right;
                }
            }
            if(sum >= k){
                sum -= sequence[left++];
            }
            else if(sum < k){
                right++;
                if(right >= sequence.length) continue;
                sum += sequence[right];
            }
            
        }
        
        return answer;
    }
}