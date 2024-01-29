import java.util.*;

class Solution {
    
    private class Point implements Comparable<Point>{
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Point p){ 
            if(Integer.compare(p.x, x) == 0){ 
                 return Integer.compare(y,p.y);
            }
            return Integer.compare(x,p.x); 
        }
    }
    
    private boolean[][] visited;
    private char SEA = 'X';
    
    private int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public int[] solution(String[] maps) {
        int[] answer = {};
        int n = maps.length;
        int m = maps[0].length();
        List<Integer> list = new ArrayList<>();
        
        visited =new boolean[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j< m; j++){  
               if(maps[i].charAt(j) != SEA && !visited[i][j]){
                   int sum = bfs(i,j,n,m,maps);
                   list.add(sum);
               }

            }
        }
        if(list.size() == 0){
            list.add(-1);
        }
        Collections.sort(list);
        return list.stream().mapToInt(Integer :: intValue).toArray();
    }
    
    
    public int bfs(int x, int y, int n, int m, String[] maps){
       
        int sum = Integer.parseInt(String.valueOf(maps[x].charAt(y)));
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Point(x,y));
        
        while(!q.isEmpty()){   
            Point p = q.poll();
            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if( nx >= 0 && ny >= 0 && nx < n && ny < m){
                    if(!visited[nx][ny] && maps[nx].charAt(ny) != SEA){
                        visited[nx][ny] = true;
                        q.offer(new Point(nx,ny));
                        sum += Integer.parseInt(String.valueOf(maps[nx].charAt(ny)));
                    }
                }
            }
          }
          return sum;
        }
        
    }
