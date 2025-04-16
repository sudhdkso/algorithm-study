import java.util.Set;
import java.util.HashSet;

class Solution {
    private static int min = Integer.MAX_VALUE;
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        Set<String> set = new HashSet<>();
        dfs(info, 0, 0, n, m, 0, new HashSet<>());
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private static void dfs(int[][] info, int a, int b, int n, int m, int index, Set<String> isKey){
        if(index >= info.length){
            min = Math.min(min, a);
            return;
        }
        
        String key = a+" "+b+" "+index;
        if(isKey.contains(key)){
            return;
        }
        if(a + info[index][0] < n){
             dfs(info, a + info[index][0], b, n, m, index+1, isKey);     
        }
        if(b + info[index][1] < m){
            dfs(info, a, b + info[index][1], n, m, index+1, isKey);   
        }
        isKey.add(key);
    }
}