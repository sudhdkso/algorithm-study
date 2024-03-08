import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    int MAX = 1_000_001;
    int DONUT = 1, STICK = 2, EIGHT = 3;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int[] in = new int[MAX],out = new int[MAX];
        
        for(int i=0;i<edges.length;i++){
            int a = edges[i][0];
            int b = edges[i][1];
            in[b]++; out[a]++;
        }
        
        int root = findRootNode(in,out);
        
        for(int i=1;i<MAX;i++){
            int index = checkGraph(i, in, out);
            if(index > 0){
                answer[index]++;
            }
        }
        
        answer[0] = root;
        answer[DONUT] = out[root] - (answer[STICK]+answer[EIGHT]);
        
        return answer;
    }
    //루트 노드의 조건 해당 노드로 부터 나가는 간선 최소 2개, 들어오는 간선 0개
    private int findRootNode(int[] in, int[] out){
        for(int i=1;i<MAX;i++){
            if(in[i] == 0 && out[i] >= 2){
                return i;
            }
        }
        return -1;
    }
    
    private int checkGraph(int node, int[] in, int[] out){
        //나가는 노드가 없으면 막대그래프
        if(out[node] == 0 && in[node] > 0){
            return STICK;
        }
        if(out[node] == 2 && in[node] > 0){
            return EIGHT;
        }
        return 0;
    }
}