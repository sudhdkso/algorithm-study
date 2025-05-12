class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(target, 0, 0, numbers);
        return answer;
    }
    
    private static int dfs(int target, int depth, int result, int[] numbers){
        if(depth >= numbers.length){
            if(result == target) return 1;
            return 0;
        }
        
        int answer = 0;
        answer += dfs(target, depth+1, result + numbers[depth], numbers);
        answer += dfs(target, depth+1, result - numbers[depth], numbers);
        
        return answer;
    }
}