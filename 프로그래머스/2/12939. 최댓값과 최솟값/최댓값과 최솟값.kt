import java.util.*

class Solution {
    fun solution(s: String): String {
        var answer = ""
        val numbers = s.split(" ")
        
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        
        for(a in numbers) {
            val num = a.toInt() 
            min = Math.min(min, num)
            max = Math.max(max, num)
        }
        
        return "$min $max"
    }
}