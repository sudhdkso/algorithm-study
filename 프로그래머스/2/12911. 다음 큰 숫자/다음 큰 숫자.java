class Solution {
    public int solution(int n) {
        int answer = 0;
        
        return findNext(n, Integer.bitCount(n));
    }
    //n과 n을 이진수로 변환했을 때 1의 갯수를 매개변수로 받아 다음 큰 수를 반환하는 함수
    private int findNext(int n, int oneCount){
        int next = n;
        
        while(true){
            next++;
            //next를 이진수로 변환한 1의 갯수를 찾고
            int nextOneCount = Integer.bitCount(next);
            //n의 1의 갯수와 같으면 바로 return
            if(oneCount == nextOneCount){
                return next;
            }
        }
    }
}