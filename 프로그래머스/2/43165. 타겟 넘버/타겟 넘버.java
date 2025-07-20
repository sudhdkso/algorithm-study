class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        return dfs(0,0,target,numbers);
    }
    
    public static int dfs(int depth, int result, int target, int[] numbers){
        if(depth > numbers.length-1){
            if(result == target) return 1;
            return 0;
        }
        
        int total = 0;
        total += dfs(depth+1, result+numbers[depth], target, numbers);
        total += dfs(depth+1, result-numbers[depth], target, numbers);
        
        return total;
    }
}