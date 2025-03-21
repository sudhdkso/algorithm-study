import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private class Server {
        int count = 0;
        int end = 0;
        
        public Server(int count, int end){
            this.count = count;
            this.end = end;
        }
    }
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Server> servers = new LinkedList<>();
        int n = 0; //현재 증설된 서버의 수
        for(int time=0;time<players.length;time++){
            int now = players[time];
            if(!servers.isEmpty() && time > servers.peek().end){
                n -= servers.poll().count;
            }
            if(now < (n+1) * m){
                continue;
            }
            int expansion = (now/m)-n;
            n += expansion;
            answer += expansion;
            servers.offer(new Server(expansion,time+k-1));
        }
        
        
        return answer;
    }
}