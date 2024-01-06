import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    private int MAX_X = 5, MAX_Y = 5, MIN_X = -5, MIN_Y = -5;
    
    private int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};

    private Map<String, Integer> key = new HashMap<>();
    public int solution(String dirs) {
        int x = 0, y = 0;
        Set<String> visited = new HashSet<>();
        
        setting();
        
        for(String dir : dirs.split("")) {
            int index = key.get(dir);
            
            int nx = x + dx[index];
            int ny = y + dy[index];
            if(nx >= MIN_X && ny >= MIN_Y && nx <= MAX_X && ny <= MAX_Y){
                visited.add(nx+" "+ny+" "+x+" "+y);
                visited.add(x+" "+y+" "+nx+" "+ny);
                x = nx;
                y = ny;
            }
        }
        return visited.size()/2;
    }
    
    private void setting(){
        String direction = "UDRL";
        for(int i=0;i<direction.length();i++){
            key.put(direction.substring(i,i+1),i);
        }
    }
    
}