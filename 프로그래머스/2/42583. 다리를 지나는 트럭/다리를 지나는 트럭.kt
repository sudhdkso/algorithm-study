import java.util.*

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val bridge : Queue<Int> = LinkedList(List(bridge_length){0})
        val trucks : Queue<Int> = LinkedList()
        var bridge_weight = 0
        
        trucks.addAll(truck_weights.toList())
        
        
        while(!trucks.isEmpty()) {
            answer++
            bridge_weight -= bridge.poll()
            if(bridge_weight + trucks.peek() <= weight) {
                bridge_weight += trucks.peek()
                bridge.offer(trucks.poll())
            } else{
                bridge.offer(0)
            }
            
        }
        
        return answer + bridge_length
    }
}