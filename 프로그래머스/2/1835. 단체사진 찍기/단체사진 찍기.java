import java.util.Queue;
import java.util.LinkedList;

class Solution {
    Queue<String> comb;
    char[] kakao;
    
    public int solution(int n, String[] data) {
        int answer = 0;
        comb = new LinkedList<>();
        kakao = new char[]{'A','C','F','J','M','N','R','T'};
        getCombination(0, new char[8], new boolean[8]);
        //System.out.println(comb.get(0)+" "+data[0]);
        while(!comb.isEmpty()){
            String c = comb.poll();
            answer++;
            for(int i=0;i<n;i++){
                if(!check(c, data[i])){
                    answer--;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    
    private void getCombination(int depth, char[] position, boolean[] visited){
        if(depth >= 8){
            comb.offer(String.valueOf(position));      
            return;
        }
        
        for(int i=0;i<8;i++){
            if(!visited[i]){
                visited[i] = true;
                position[depth] = kakao[i];
                getCombination(depth+1, position, visited);
                visited[i] = false;
            }
        }
    }
    
    private boolean check(String position, String data){
        
        char[] dataArr = data.toCharArray();
        int p1 = position.indexOf(dataArr[0]);
        int p2 = position.indexOf(dataArr[2]);
        int target = Integer.parseInt(String.valueOf(dataArr[4]))+1;
        int dist = Math.abs(p1-p2);
        switch(dataArr[3]){
            case '=' :
                return dist == target;
            case '>' :
                return dist > target;
            case '<' :
                return dist < target;
        }
        return false;
    }
}