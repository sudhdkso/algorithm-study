import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static class Node{
        String word;
        int depth;
        public Node(String word, int depth){
            this.word = word;
            this.depth = depth;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        return bfs(words, begin, target);
    }
    
    private static boolean canChange(char[] s1, char[] s2){
        int count = 0;
        for(int i=0;i<s1.length;i++){
            if(s1[i] != s2[i]){
                count++;
            }
            if(count >= 2){
                return false;
            }
        }
        return true;
    }
    
    private static int bfs(String[] words, String begin, String target){
        boolean[] visited = new boolean[words.length];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(begin, 0));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.word.equals(target)){
                return cur.depth;
            }
            
            for(int i=0;i<words.length;i++){
                if(!visited[i] && canChange(cur.word.toCharArray(), words[i].toCharArray())){
                    visited[i] = true;
                    q.offer(new Node(words[i], cur.depth+1));
                }
            }
        }
        return 0;
    }
}