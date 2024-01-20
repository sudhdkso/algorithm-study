class Solution {
    private int[] a = {4,1,2};
    
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        
        while(n > 0){ 
          answer.append(a[n%3]);
          n = (n-1)/3;
        }
    
        return answer.reverse().toString();
    }
    
    private String convert(int n){
       
        String result = "";
        if(n >3){
          result = String.valueOf(convert(n/3));
        }
        else{
          result = String.valueOf(a[n%3]);
        }
        return result;
    }
}