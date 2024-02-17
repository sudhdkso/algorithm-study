import java.util.*;

class Solution {
    private class Point implements Comparable<Point>{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString(){
            return "[x : "+x+" , y: "+y+"]\n";
        }
        
        @Override
        public int compareTo(Point p){
            if(x == p.x){
                return Integer.compare(y, p.y);
            }
            return Integer.compare(x,p.x);
        }
    }
    
    private class Puzzle {
        List<Point> puzzle;
        
        public Puzzle(){
            puzzle = new ArrayList<>();
        }
        
        
        public void addPoint(Point p){
            puzzle.add(p);
        }
        
        
        public int getMinX(){
            int minX = Integer.MAX_VALUE;
            for(Point p : puzzle){
                minX = Math.min(minX, p.x);
            }
            return minX;
        }
        
        public int getMinY(){
            int minY = Integer.MAX_VALUE;
            for(Point p : puzzle){
                minY = Math.min(minY, p.y);
            }
            return minY;
        }
        
        public void sortPoint(){
            Collections.sort(puzzle);
        }
        
        public int getSize(){
            return puzzle.size();
        }
        
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("========퍼즐조각========\n");
            for(Point p : puzzle){
                sb.append(p.toString());
            }
            return sb.toString();
        }
    }
    
    private List<Puzzle> puzzles = new ArrayList<>();
    private List<Puzzle> emptyPuzzles = new ArrayList<>();
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        setPuzzle(table);
        setBoard(game_board);
        
        answer = matchPuzzles();
        
        return answer;
    }
    
    private void setPuzzle(int[][] board){
        int n = board.length;
        int m = board[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && board[i][j] == 1){
                    puzzles.add(bfs(i,j,visited,board,1));
                }
            }
        }
    }
    
    private void setBoard(int[][] board){
        int n = board.length;
        int m = board[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && board[i][j] == 0){
                    emptyPuzzles.add(bfs(i,j,visited,board,0));
                }
            }
        }
    }
    
    private int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    
    private Puzzle bfs(int x, int y,boolean[][] visited, int[][] board, int target){
        Queue<Point> q = new LinkedList<>();
        int n = board.length;
        int m = board[0].length;
        
        visited[x][y] = true;
        Puzzle puzzle = new Puzzle();
        q.offer(new Point(x,y));
        
        puzzle.addPoint(new Point(0,0));
        
        while(!q.isEmpty()){
            Point p = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx >= 0 && ny >= 0 && nx < n && ny < m){
                    if(!visited[nx][ny] && board[nx][ny] == target){
                        q.offer(new Point(nx, ny));
                        puzzle.addPoint(new Point(nx-x, ny-y));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        puzzle.sortPoint();
        return alignLeft(puzzle);
    }
    
    
    //퍼즐 맞는지 확인하는 함수
    private int matchPuzzles(){
        int answer = 0;
        boolean[] visited = new boolean[puzzles.size()];
        for(Puzzle ep : emptyPuzzles){
            for(int j=0;j<puzzles.size();j++){
                Puzzle p = puzzles.get(j);
                if(ep.getSize() == p.getSize() && !visited[j]){
                    if(isRotate(ep, p)){
                        answer += ep.getSize();
                        visited[j] = true;
                        break;
                    }
                }
            }
        }
        return answer;
    }
    
    //퍼즐 90도 회전하는 함수
    private Puzzle rotate90(Puzzle p){
        Puzzle rotated = new Puzzle();
        
        for(Point point : p.puzzle){
            rotated.addPoint(new Point(point.y, -point.x));
        }
        
        return rotated;
    }
    
    //(0,0)기준으로 정렬하는 함수
    private Puzzle alignLeft(Puzzle p){
        Puzzle align = new Puzzle();
        int minX = p.getMinX();
        int minY = p.getMinY();
        
        for(Point point : p.puzzle){
            align.addPoint(new Point(point.x - minX, point.y - minY));
        }
        
        return align;
    }
    
    private boolean isRotate(Puzzle ep , Puzzle p){
        
        for(int i=0;i<4;i++){
            
            if(isCollectedPuzzle(ep, p)){
                return true;
            }
            p = alignLeft(rotate90(p));
            p.sortPoint();
        }
        return false;
    }
    
    //ep와 p가 같은 조각인지 확인하는 함수
    private boolean isCollectedPuzzle(Puzzle ep, Puzzle p){
        for(int i=0;i<ep.getSize();i++){
            Point ePoint = ep.puzzle.get(i);
            Point pPoint = p.puzzle.get(i);
           
            if(ePoint.x != pPoint.x || ePoint.y != pPoint.y){
                return false;
            }
            
        }
        return true;
    }
}