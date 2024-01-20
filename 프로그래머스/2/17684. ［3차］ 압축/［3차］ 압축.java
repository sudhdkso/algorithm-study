import java.util.List;
import java.util.ArrayList;

class Solution {
    public List<Integer> solution(String msg) {
        
        List<String> dict = init();
        List<Integer> answer = new ArrayList<>();
        
        int length = msg.length();
        
        for(int i=0;i<length;i++){
             StringBuilder w = new StringBuilder(String.valueOf(msg.charAt(i)));
            if(i >= length-1){
               answer.add(dict.indexOf(w.toString())+1);
                break;
            }
            
            
            String c = String.valueOf(msg.charAt(i+1));
            while(dict.contains(w+c)){
                w.append(c);
                i++;
                if(i >= length-1){
                  break;
                }
                
                c = String.valueOf(msg.charAt(i+1));
                
            }
            String next = String.valueOf(w+c);
            int index = dict.indexOf(w.toString())+1;
            if(i >= length-1){
               answer.add(index);
                break;
            }
            if(!dict.contains(next)){
               dict.add(next);
               answer.add(index);
            
            }

            
        }
        return answer;
    }
    
    private List<String> init(){
        List<String> dict = new ArrayList<>();
        
        for(int i=0; i< 26;i++){
           dict.add(String.valueOf((char)('A'+i)));
        }
        return dict;
    }
}