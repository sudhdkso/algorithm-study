import java.util.Stack;
import java.util.Arrays;
class Solution {
    public String solution(String p) {
        String answer = "";
        
        return convertCollectBracket(p);
    }
    
    private String convertCollectBracket(String p){
        int index = splitBracket(p);
        //1.
        if(index == -1) return "";
        //2.
        String u = p.substring(0,index+1);
        String v = p.substring(index+1, p.length());
        //3.
        if(isCollectBracket(u)){
            return u + convertCollectBracket(v);
        }
        //4.
        else{
            //4-1,2,3
            String v1 = String.format("(%s)", convertCollectBracket(v));
            return v1 + rotateBracket(u.substring(1, u.length() - 1));
        }
    }
    
    private int splitBracket(String w){
        int[] arr = new int[2];
        int index = 0;
        if(w.equals("")) return -1;
        for(int i=0;i<w.length();i++){
            if(w.charAt(i) == '('){
                arr[0]++;
            }
            else if(w.charAt(i) == ')'){
                arr[1]++;
            }
            
            if(arr[0] == arr[1]){
                index = i;
                break;
            }
        }
        return index;
    }
    
    private boolean isCollectBracket(String u){
        Stack<Character> s = new Stack<>();
        
        for(char c : u.toCharArray()){
            if(c == '('){
                s.push(c);
            } 
            else if(!s.isEmpty() && c == ')'){
                s.pop();
            }
        }
        return s.isEmpty();
    }
    
    private String rotateBracket(String u){
        StringBuilder sb = new StringBuilder();
        
        for(String s : u.split("")){
            if(s.equals("(")){
                sb.append(")");
            }
            else if(s.equals(")")){
                sb.append("(");
            }
        }
        return  sb.toString();
    }
}