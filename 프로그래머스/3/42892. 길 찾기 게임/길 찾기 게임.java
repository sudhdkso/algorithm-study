import java.util.*;

class Solution {
    private class Node {
        int x, y;
        int index;
        Node left, right;
        public Node(int x, int y,int index, Node left, Node right){
            this.x = x;
            this.y = y;
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }
    public static int[][] answer;
    private static int index = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        
        Node[] node = new Node[n];
        
        for(int i=0;i<n;i++){
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1],i+1,null, null);
        }
        
        Arrays.sort(node, (o1,o2) -> {
            if(o1.y == o2.y) return o1.x - o2.x;
            return o2.y - o1.y;
        });
            
        Node root = node[0];
        
        for(int i=1;i<n;i++){
            setNode(root, node[i]);
        }
        
        answer = new int[2][n];

        preorder(root);
        index = 0;
        postorder(root);
        return answer;
    }
    
    private static void setNode(Node parent, Node child){
        if(parent.x > child.x){
            if(parent.left == null) parent.left = child;
            else setNode(parent.left, child);
        }
        else {
            if(parent.right == null) parent.right = child;
            else setNode(parent.right, child);
        }
    }
    
    private static void preorder(Node root) {
        if(root != null){
            answer[0][index++] = root.index;
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    private static void postorder(Node root) {
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            answer[1][index++] = root.index;
        }
    }
}