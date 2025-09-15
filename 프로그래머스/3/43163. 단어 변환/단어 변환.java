class Solution {
    private static int min = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        dfs(new boolean[words.length], words, begin, target, 0);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    private static void dfs(boolean[] visited, String[] words, String now, String target, int depth){
        if(now.equals(target)){
            min = Math.min(min, depth);
            return;
        }
        int result = Integer.MAX_VALUE;
        for(int i=0;i<words.length;i++){
            if(!visited[i] && canChange(now.toCharArray(), words[i].toCharArray())){
                visited[i] = true;
                dfs(visited, words, words[i], target, depth+1);
                visited[i] = false; 
            }
        }
        return;
    }
    
    private static boolean canChange(char[] s1, char[] s2){
        int count = 0;
        for(int i=0;i<s1.length;i++){
            if(s1[i] != s2[i]){
                count++;
            }
            if(count >= 2){
                return false;
            }
        }
        return true;
    }
}