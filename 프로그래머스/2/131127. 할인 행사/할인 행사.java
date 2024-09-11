import java.util.Map;
import java.util.HashMap;

class Solution {
    private static Map<String, Integer> map = new HashMap<>();
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int range = 10, initSize = discount.length > 10 ? 10 : discount.length;
        
        for(int i=0;i<initSize;i++){
            map.put(discount[i], map.getOrDefault(discount[i],0)+1);
        }
        
        for(int i=0;i<discount.length;i++){
            if(check(want, number)){
                answer++;
            }

            map.put(discount[i],map.getOrDefault(discount[i],0)-1);
            
            if(map.get(discount[i]) <= 0){
                map.remove(discount[i]);
            }
            if(i+10 < discount.length){
                map.put(discount[i+10],map.getOrDefault(discount[i+10],0)+1);             
            }
            
        }
        
        return answer;
    }
    
    private static boolean check(String[] want, int[] number){
        
        for(int i=0;i<want.length;i++){
            if(map.getOrDefault(want[i],0) < number[i]){
                return false;
            }
        }
        return true;
    }
    
    private static void find(String[] discount){
        
    
    }
}