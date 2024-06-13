import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    private static char START = 'S', LEVER = 'L', WALL = 'X', PATH = 'O', END = 'E';
    public int solution(String[] maps) {
        int answer = 0;
        
        int n = maps.length;
        int m = maps[0].length();
        
        char[][] ch = new char[n][m];
        
        for(int i=0;i<n;i++){
            ch[i] = maps[i].toCharArray();
        }
        
        int sToL = bfs(START,LEVER, n , m ,ch);
        int lToE = bfs(LEVER,END, n , m ,ch);
        answer = (sToL == -1 || lToE == -1) ? -1 : sToL+lToE;
        return answer;
    }
    
    private Integer[] findPosition(char target, char[][] maps){
        int n = maps.length;
        int m = maps[0].length;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(maps[i][j] == target){
                    return new Integer[]{i,j};
                }
            }
        }
        return new Integer[]{-1,-1};
    }
    
    int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    
    private int bfs(char st, char dt, int n, int m, char[][] maps){
        Queue<Integer[]> q = new LinkedList<>();
        
        Integer[] s = findPosition(st, maps);
        q.offer(s);
        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];
        
        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        
        dist[s[0]][s[1]] = 0;
        visited[s[0]][s[1]] = true;
        
        while(!q.isEmpty()){
            Integer[] now = q.poll();
            if(maps[now[0]][now[1]] == dt){
                return dist[now[0]][now[1]];
            }
            for(int i=0;i<4;i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                
                if(nx >=0 && ny >= 0 && nx < n && ny < m){
                    if(visited[nx][ny] || maps[nx][ny] == WALL) continue;
                    if(dist[nx][ny] > dist[now[0]][now[1]] + 1){
                        dist[nx][ny] = dist[now[0]][now[1]] + 1;
                        q.offer(new Integer[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                    
                }
            }
        }
        return -1;
    }
}