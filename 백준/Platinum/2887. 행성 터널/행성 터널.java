import java.io.*;
import java.util.*;

public class Main {

    private static class Edge implements Comparable<Edge>{
        int nodeA,nodeB,cost;

        public Edge(int nodeA,int nodeB, int cost){
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.cost, e.cost);
        }
    }
    static int[] parent = new int[100_001];
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        List<int[]> point = new ArrayList<>();

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            point.add(new int[]{i,x,y,z});
        }

        for(int i=1;i<=3;i++){
            int v = i;
            Collections.sort(point, (o1,o2) -> Integer.compare(o1[v],o2[v]));
            for(int j=0;j<N-1;j++){
                int[] p1 = point.get(j);
                int[] p2 = point.get(j+1);
                edges.add(new Edge(p1[0],p2[0], Math.abs(p1[v]-p2[v])));
            }
        }

        Collections.sort(edges);

        for(int i=0;i<N;i++){
            parent[i] = i;
        }

        bw.write(String.valueOf(kruskal()));
        bw.flush();
    }

    private static int kruskal(){
        int sum = 0;
        for(Edge e: edges){
            int a = e.nodeA;
            int b = e.nodeB;
            int c = e.cost;
            if(findParent(a) != findParent(b)){
                union(a,b);
                sum += c;
            }
        }

        return sum;
    }

    private static int findParent(int x){
        if(x == parent[x]){
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    private static void union(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a < b){
            parent[b] = a;
        }
        else{
            parent[a] = b;
        }
    }
}