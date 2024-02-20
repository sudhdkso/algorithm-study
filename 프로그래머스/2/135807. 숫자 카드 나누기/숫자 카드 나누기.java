import java.util.Arrays;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        int a = arrayA[0];
        int b = arrayB[0];
        
        for(int i=1;i<arrayA.length;i++){
            a = gcd(a, arrayA[i]);
        }
        
        for(int i=0;i<arrayB.length;i++){
            b = gcd(b, arrayB[i]);
        }
        
        if(!isDivided(a, arrayB)){
            answer = a;
        }
        if(!isDivided(b, arrayA)){
            answer = Math.max(a,b);
        }

        return answer;
    }
    
    public int gcd(int a,int b){
        if( b ==0) return a;
        return gcd(b,a%b);
    }
    
    public boolean isDivided(int num, int[] arr){
        
        for(int i=0;i<arr.length;i++){
            if(arr[i]%num == 0){
                return true;
            }
        }
        return false;
    }
}