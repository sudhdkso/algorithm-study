import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        Stack<Integer> s = new Stack<>();
        
        for(int a: arr){
            if(!s.isEmpty() && s.peek() == a) continue;
            s.push(a);
        }

        return s.stream().mapToInt(Integer::intValue).toArray();
    }
}