class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        return dfs(0,0,numbers,target);
    }
    
    private static int dfs(int now, int index, int[] numbers, int target){
        if(index == numbers.length){
            if(now == target){
                return 1;
            }
            return 0;
        }
        
        int answer = 0;
        
        answer += dfs(now+numbers[index], index+1, numbers, target);
        answer += dfs(now-numbers[index], index+1, numbers, target);
        return answer;
        
    }
}