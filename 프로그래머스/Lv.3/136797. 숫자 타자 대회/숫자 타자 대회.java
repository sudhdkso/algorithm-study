import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
class Solution {
    
    private class Position{
        int left, right;
        
        public Position(int left, int right){
            this.left = left;
            this.right = right;
        }
        
        @Override
        public boolean equals(Object o){
            Position p = (Position) o;
            return (p.left == left && p.right == right) || (p.left == right && p.right == left);
        }
        
        @Override
        public int hashCode() {
            return left;
        }
        
        @Override
        public String toString(){
            return "[ left: "+left+" , right"+right+"]\n";
        }
    }
    private int[][] weight = {
        {1,7,6,7,5,4,5,3,2,3},
        {7,1,2,4,2,3,5,4,5,6},
        {6,2,1,2,3,2,3,5,4,5},
        {7,4,2,1,5,3,2,6,5,4},
        {5,2,3,5,1,2,4,2,3,5},
        {4,3,2,3,2,1,2,3,2,3},
        {5,5,3,2,4,2,1,5,3,2},
        {3,4,5,6,2,3,5,1,2,4},
        {2,5,4,5,3,2,3,2,1,2},
        {3,6,5,4,5,3,2,4,2,1}
    };
    
    public int solution(String numbers) {
        
        Map<Position, Integer> current = new HashMap<>();
        
        current.put(new Position(4,6), 0);
        
        for(String number : numbers.split("")){
            int num = Integer.parseInt(number);
            current = moveNextNumber(current, num);
        }
        //System.out.println(current);
        return getMinWeight(current);
    }
    
    public Map<Position, Integer> moveNextNumber(Map<Position, Integer> current, int number){
        
        Map<Position, Integer> move = new HashMap<>();
        
        for(Position p : current.keySet()){
            int lc = p.left;
            int rc = p.right;
            int wc = current.get(p);
            
            Position lm = new Position(number, rc);
            Position rm = new Position(lc, number);
            
            //왼손에 위치랑 다음 숫자랑 같을 때
            if(number == lc){
                if(check(move, lm, wc+1)){
                    move.put(lm, wc+1);
                }
            }
            //오른손에 위치랑 다음 숫자랑 같을 때
            else if(number == rc){
                if(check(move, rm, wc+1)){
                    move.put(rm, wc+1);
                }
            }
            else{
                int nextLm = wc+calcWeight(p, lm);
                int nextRm = wc+calcWeight(p, rm);
                if(check(move, lm, nextLm)){
                    move.put(lm, nextLm);
                }
                if(check(move, rm, nextRm)){
                    move.put(rm, nextRm);
                }
            }
            
        }
        
        return move;
    }
    
    private boolean check(Map<Position, Integer> move, Position p, int weight){
        
        if((move.containsKey(p)) && move.get(p) < weight) {
            return false;
        }
        return true;
        
    }
    
    private int calcWeight(Position cur, Position next){
        if(cur.left == next.left && cur.right == next.right){
            return weight[cur.left][next.left];
        }
        else if(cur.left == next.left){
            return weight[cur.right][next.right];
        }
        return weight[cur.left][next.left];
    }
    
    private int getMinWeight(Map<Position, Integer> current){
        int min = Integer.MAX_VALUE;
        for(Position p : current.keySet()){
            min = Math.min(min, current.get(p));
        }
        return min;
    }
}