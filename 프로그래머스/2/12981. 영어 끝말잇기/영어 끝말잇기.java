import java.util.Set;
import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};
        Set<String> set = new HashSet<>();
        
        set.add(words[0]);
        
        for(int i=1;i<words.length;i++){
            String s1 = words[i-1];
            String s2 = words[i];
            
            if(set.contains(s2) || s1.charAt(s1.length()-1) != s2.charAt(0)){
                return new int[]{(i%n)+1, (i/n)+1};
            }
            set.add(s2);
        }

        return answer;
    }
}