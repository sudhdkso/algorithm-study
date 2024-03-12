import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        int kind = new HashSet<>(Arrays.asList(gems)).size();
        Map<String, Integer> gemsMap = new HashMap<>();
        
        List<int[]> list = new ArrayList<>();
        int left = 0;
        for(int i=0;i<gems.length;i++){
            gemsMap.put(gems[i], gemsMap.getOrDefault(gems[i],0)+1);
            
            if(gemsMap.size() == kind){
                while(gemsMap.get(gems[left]) > 1){
                    gemsMap.put(gems[left], gemsMap.get(gems[left])-1);
                    left++;
                }
                
                list.add(new int[]{left+1, i+1});
            }
        }
        return getMinSection(list);
    }
    
    private int[] getMinSection(List<int[]> list){
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        for(int[] arr : list){
            int len = arr[1] - arr[0];
            if(min == len) continue;
            if(min > len){
                min = len;
                answer = arr;
            }
           
        }
        return answer;
    }

}