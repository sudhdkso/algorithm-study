import java.util.Stack;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        if(!isConvertBeginToTarget(target, words)){
            return answer;
        }
        
        dfs(0, begin, target, new boolean[words.length], words);
        
        return min;
    }
    
    private boolean isConvertBeginToTarget(String target, String[] words){
        for(int i=0;i<words.length;i++){
            if(words[i].equals(target)){
                return true;
            }
        }    
        return false;
    }
    
    private static int min = Integer.MAX_VALUE;
    
    private void dfs(int depth, String current, String target, boolean[] visited, String[] words){
        
        if(current.equals(target)){
            min = Math.min(depth, min);
            return;
        }
        
        if(depth >= visited.length){
            return;
        }
        
        for(int i=0;i<words.length;i++){
            if(!visited[i] && isConvert(current, words[i])){
                visited[i] = true;
                dfs(depth+1, words[i], target, visited, words);
                visited[i] = false;
            }
        }
    }
    
    
    private boolean isConvert(String current , String target){
        int count = 0;
        for(int i=0;i<current.length();i++){
            if(current.charAt(i) != target.charAt(i)){
                count++;
            }
            if(count > 1){
                return false;
            }
        }
        
        return count == 1;
    }
}