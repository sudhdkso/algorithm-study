class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

       for(int i=0;i<s.length;i++){
           answer[i] = move110s(s[i]);
       }
        return answer;
    }
    
    private static String move110s(String s){
        int count = 0;
        StringBuilder sb = new StringBuilder();
        
        for(char ch : s.toCharArray()){
            sb.append(ch);
            int len = sb.length();
            if(len >= 3){
                String s1 = sb.substring(len-3, len);
                if(s1.equals("110")){
                    count++;
                    sb.delete(len-3, len);
                }
            }
        }
        
        int index = sb.lastIndexOf("0");
        int p = index < 0 ? 0 : index+1;
        sb.insert(p, "110".repeat(count));
        
        return sb.toString();
    }
}