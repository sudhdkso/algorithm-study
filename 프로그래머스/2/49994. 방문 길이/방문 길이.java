import java.util.Set;
import java.util.HashSet;

class Solution {
    private static int MIN_X = -5, MIN_Y = -5, MAX_X = 5, MAX_Y = 5;
    private static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    
    public int solution(String dirs) {
        int answer = 0;
        Set<String> set = new HashSet<>();
        
        int sx = 0, sy = 0;
        
        for(char dir : dirs.toCharArray()){
            int move = getMoveDir(dir);
            int nx = sx + dx[move];
            int ny = sy + dy[move];
            
            if(nx >= MIN_X && ny >= MIN_Y && nx <= MAX_X && ny <= MAX_Y){
                
                set.add(sx+" "+sy+" "+nx+" "+ny);
                set.add(nx+" "+ny+" "+sx+" "+sy);

                sx = nx;
                sy = ny;    
            }
 
                      
        }
        return set.size()/2;
    }
    
    private static int getMoveDir(char ch){
        if(ch == 'U'){
            return 0;
        }
        else if(ch == 'D'){
            return 1;
        }
        else if(ch == 'R'){
            return 2;
        }
        else {
            return 3;
        }
    }
}