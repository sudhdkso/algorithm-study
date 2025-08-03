import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        List<String> list = Arrays.stream(gems).collect(Collectors.toList());
        Set<String> set = new HashSet<>(list);
        Map<String, Integer> map = new HashMap<>();
        
        int kinds = set.size();
        int left = 0;
        
        for(int i=0;i<gems.length;i++){
            map.put(gems[i], map.getOrDefault(gems[i], 0 )+1);
            
            if(map.size() == kinds){ 
                while(map.get(gems[left]) > 1){
                    map.put(gems[left], map.get(gems[left])-1);
                    left++;
                }
                if(isMinRange(answer, new int[]{left+1, i+1})){
                        answer = new int[]{left+1, i+1};
                }
            }
        }
        return answer;
    }
    
    private static boolean isMinRange(int[] arr1, int[] arr2){
        if(arr1[0] == 0 && arr1[1] == 0) return true;
        int len1 = arr1[1] - arr1[0];
        int len2 = arr2[1] - arr2[0];
        
        return len1 > len2;
    }
}