class Solution {
    fun solution(n: Int): Int {
        var answer = 0

        var dp = IntArray(n+1,{0})
        dp[0] = 0
        dp[1] = 1
        for(i : Int in 2 .. n){
            dp[i] = (dp[i-1] + dp[i-2])%1234567
        }
        
        return dp[n]
    }
}