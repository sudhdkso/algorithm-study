import java.util.*;
class Solution {
    private Map<String, ArrayList<Integer>> scoreByInfo = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        getAllCombination(info);
        
        for(String key : scoreByInfo.keySet()){
            Collections.sort(scoreByInfo.get(key));
        }
        
        for(int i=0;i<query.length;i++){
            String[] str = query[i].replaceAll(" and "," ").split(" ");
            String q = String.join(" ",Arrays.copyOfRange(str, 0, 4));
            int score = Integer.parseInt(str[4]);
            answer[i] = getTargetCountByQuery(q, score);
        }
        return answer;
    }
    
    private int getTargetCountByQuery(String query, int targetScore){
        if(!scoreByInfo.containsKey(query)){
            return 0;
        }
        
        ArrayList<Integer> scores = scoreByInfo.get(query);

        return binarySearch(targetScore, scores);
    }
    
    private int binarySearch(int target, ArrayList<Integer> scores){
        int low = 0, high = scores.size()-1;
        int result = 0;
       
        while(low <= high){
            int mid = (low + high)/2;
            int score = scores.get(mid);
            if(score < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return scores.size()-low;
    }
    private void getAllCombination(String[] info){
        for(String i : info){
            getCombinationByInfo(0, i.split(" "), new String[4]);
        }
    }
    
    private void getCombinationByInfo(int depth, String[] info, String[] comb){
        
        if(depth == 4){
            String combStr = String.join(" ", comb);
            if(!scoreByInfo.containsKey(combStr)){
                scoreByInfo.put(combStr, new ArrayList<>());
            }
            scoreByInfo.get(combStr).add(Integer.parseInt(info[depth]));
            return;
        }
        comb[depth] = info[depth];
        getCombinationByInfo(depth+1, info, comb);
        comb[depth] = "-";
        getCombinationByInfo(depth+1, info, comb);
        
    }
}