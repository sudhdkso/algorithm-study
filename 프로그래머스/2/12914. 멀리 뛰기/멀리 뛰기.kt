class Solution {
    fun solution(n: Int): Long {
        var answer: Long = 0
        var dp = LongArray(n+1) {0}
        if(n == 1) return 1
        dp[1] = 1
        dp[2] = 2
        
        for(index in 3..n){
            dp[index] = (dp[index-1] + dp[index-2])%1234567
        }
        
        return dp[n]
    }
}