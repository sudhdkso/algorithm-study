import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        answer = dfs(0, 1, n, new boolean[n+1], new int[5], q, ans);
        return answer;
    }
    
    public int dfs(int count, int start, int n, boolean[] visited, int[] numbers, int[][] q, int[] ans){
        if(count >= 5){
            if(isCheck(numbers, q, ans)){
                return 1;
            }
            return 0;
        }
        int answer = 0;
        for(int i=start;i<=n;i++){
            if(!visited[i]){
                visited[i] = true;
                numbers[count] = i;
                answer += dfs(count+1, i, n, visited, numbers, q, ans);
                visited[i] = false;
            }
        }
        return answer;
    }
    
    public boolean isCheck(int[] numbers, int[][] q, int[] ans){
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        for(int i=0;i<q.length;i++){
            int count = 0;
            for(int j=0;j<q[i].length;j++){
                if(list.indexOf(q[i][j]) >=0){
                    count++;
                }
            }
            if(count != ans[i]){
                return false;
            }
        }
        return true;
    }
}