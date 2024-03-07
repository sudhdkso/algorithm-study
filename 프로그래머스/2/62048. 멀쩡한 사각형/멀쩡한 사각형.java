class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        int mul = gcd(w, h);
        int notUsed = (w/mul)+(h/mul) - 1;
        answer = ((long)w*(long)h) - notUsed * mul;
        return answer;
    }
    
    private int gcd(int a, int b){
        if(a%b == 0){
            return b;
        }
        return gcd(b, a%b);
    }
}