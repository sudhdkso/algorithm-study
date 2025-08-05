import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private static Set<String> bannedSet = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        go(user_id, banned_id, 0, new boolean[user_id.length]);
        
        return bannedSet.size();
    }
    
    private static void go(String[] user_id, String[] banned_id, int depth, boolean[] visited){
        if(depth >= banned_id.length){
            List<String> list = new ArrayList<>();
            
            for(int i=0;i<visited.length;i++){
                if(!visited[i]) continue;
                list.add(user_id[i]);
            }
            Collections.sort(list);
            
            bannedSet.add(String.join(",", list));
            return;
        }
        for(int i=0;i<user_id.length;i++){
            if(visited[i]) continue;
            if(isBannedId(user_id[i].toCharArray(), banned_id[depth].toCharArray())){
                visited[i] = true;
                go(user_id, banned_id, depth+1, visited);
                visited[i] = false;
            }
        }
    }
    
    private static boolean isBannedId(char[] userId, char[] bannedId){
        if(userId.length != bannedId.length) return false;
            
        for(int i=0;i<userId.length;i++){
            if(bannedId[i] == '*') continue;
            if(bannedId[i] != userId[i]){
                return false;
            }
        }
        return true;
    }
}