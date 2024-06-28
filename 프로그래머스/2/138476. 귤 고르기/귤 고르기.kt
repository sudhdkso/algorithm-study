class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        var answer: Int = 0
        var count: Int = 0
        var map : HashMap<Int, Int> = hashMapOf()
        
        for(i : Int in tangerine){
            map.put(i,map.getOrDefault(i,0).plus(1))
        }
        
        val sortMap = map
            .toList()
            .sortedByDescending{ it.second }
            .toMap()
        
        for((key,value) in sortMap){
            count = count.plus(value)
            answer = answer.plus(1)
            if(count >= k){
                break
            }
        }
        return answer
    }
}