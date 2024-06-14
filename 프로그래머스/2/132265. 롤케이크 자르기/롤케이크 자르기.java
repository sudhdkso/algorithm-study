import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Set<Integer> mine = new HashSet<>();
        Map<Integer, Integer> toppings = new HashMap<>();
        
        for(int i=0;i<topping.length;i++){
            toppings.put(topping[i], toppings.getOrDefault(topping[i],0)+1);
        }
        
        for(int i=0;i<topping.length;i++){
            mine.add(topping[i]);
            toppings.put(topping[i], toppings.get(topping[i])-1);
            if(toppings.get(topping[i]) == 0){
                toppings.remove(topping[i]);
            }
            if(toppings.size() == mine.size()){
                answer++;
            }
        }
        return answer;
    }
}