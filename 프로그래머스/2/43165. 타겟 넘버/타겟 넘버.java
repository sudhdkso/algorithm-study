class Solution {
    private static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, numbers, 0, target);
        return answer;
    }
    
    private static void dfs(int index, int[] numbers, int result, int target){
        if(index >= numbers.length){
            if(result == target){
                answer++;
            }
            return;
        }
        
        dfs(index+1,numbers, result + numbers[index], target);
        dfs(index+1,numbers, result - numbers[index], target);
        
        
    }
}