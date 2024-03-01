import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Map<Double, Integer> map = new HashMap<>();
        Arrays.sort(weights);
        
        for(int weight : weights){
            double w1 = weight*1.0;
            double w23 = (weight*2.0)/3.0;
            double w24 = (weight*2.0)/4.0;
            double w34 = (weight*3.0)/4.0;
            
            if(map.containsKey(w23)) answer += map.get(w23);
            if(map.containsKey(w24)) answer += map.get(w24);
            if(map.containsKey(w34)) answer += map.get(w34);
            
            if(map.containsKey(w1)) answer += map.get(w1);
            
            map.put(w1, map.getOrDefault(w1,0)+1);
        }
        return answer;
    }
}