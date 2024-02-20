class Solution {
    public int solution(int n) {
        int answer = 0;
        
        return findNext(n, Integer.bitCount(n));
    }
    
    private int findNext(int n, int oneCount){
        int next = n;
        
        while(next <= 1_000_000){
            next++;
            int nextOneCount = Integer.bitCount(next);
            if(oneCount == nextOneCount){
                return next;
            }
        }
        return 0;
    }
}