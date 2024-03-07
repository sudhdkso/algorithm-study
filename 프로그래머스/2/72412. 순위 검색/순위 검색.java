import java.util.*;
class Solution {
    static Map<String, ArrayList<Integer>> scores = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        //모든 조합 만들기
        for(String s : info){
            getAllCombination(0,s.split(" "), new String[4]);
        }
        
        for(String key : scores.keySet()){
            Collections.sort(scores.get(key));
        }
        
        for(int i=0;i<query.length;i++){
            String[] s = query[i].replace(" and","").split(" ");

            int score = Integer.parseInt(s[4]);
            String key = s[0]+s[1]+s[2]+s[3];
            if(!scores.containsKey(key)){
                answer[i] = 0;
                continue;
            }
            answer[i] = binarySearch(scores.get(key), score);
        }
        
        return answer;
    }
    
    private static int binarySearch(ArrayList<Integer> list, int score){
        int low = 0, high = list.size()-1;
        while(low <= high){
            int mid = (low+high)/2;
            if(list.get(mid) < score){
                low = mid + 1;
            }
            else{
                high = mid-1;
            }
            
        }
        return list.size()-low;
    }
    
    
    private static void getAllCombination(int depth, String[] info, String[] mInfo){
        if(depth == 4){
            String str = String.join("",mInfo);
            if(!scores.containsKey(str)){
                scores.put(str,new ArrayList<>());
            }
            scores.get(str).add(Integer.parseInt(info[depth]));
            return;
        }
        
        mInfo[depth] = info[depth];
        getAllCombination(depth+1,info,mInfo);
        mInfo[depth] = "-";
        getAllCombination(depth+1,info,mInfo);
        
    }
}