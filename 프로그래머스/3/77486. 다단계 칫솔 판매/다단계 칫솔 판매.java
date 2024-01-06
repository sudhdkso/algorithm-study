import java.util.*;
class Solution {
    private static class Node{
        Node parent;
        String name;
        
        public Node(Node parent, String name){
            this.parent = parent;
            this.name = name;
        }
        
        public Node(String name){
            this(null, name);
        }
        
        @Override
        public boolean equals(Object obj){
            Node e = (Node)obj;
            if(e.name.equals(this.name)){
                return true;
            }
            return false;
        }
        
        @Override
        public String toString(){
            return "["+this.name+"] \n";
        }
    }
    
    static int[] result;
    static List<Node> group = new ArrayList<>();
    static Map<String,Integer> map = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        int[] answer = new int[n];
        result = new int[n];
        
        for(int i=0;i<n;i++){
            if(referral[i].equals("-")){
                group.add(new Node(enroll[i]));
                map.put(enroll[i],i);
                continue;
            }
            int index = map.get(referral[i]);

            Node parent = group.get(index);
            
            group.add(new Node(parent, enroll[i]));
            map.put(enroll[i],i);
        }

        for(int i=0;i<seller.length;i++){
            setProfit(seller[i],amount[i]);
        }

        return result;
    }
    
    private static void setProfit(String seller, int amount){
        int money = amount*100;
        while(money > 0){
            int index = map.get(seller);
            Node now = group.get(index);
            if(money/10 <= 0){
                result[index] += money;
            }
            else{
                result[index] += money-(money/10);    
            }

            money/=10;
            if(now.parent == null){
                break;
            }
            seller = now.parent.name;
        }
    }
}