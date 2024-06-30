import java.util.*

class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        val waiting: Queue<Process> = LinkedList()

        for (index in priorities.indices) {
            waiting.offer(Process(index, priorities[index]))
        }

        val pq: PriorityQueue<Int> = PriorityQueue(Comparator<Int> { a, b -> b - a })
        pq.addAll(priorities.toList())

        while (true) {
            val now = waiting.poll()
            if (pq.peek() == now.priority) {
                answer++
                pq.poll()
                if(now.index == location) break
            }else{
                waiting.offer(now)
            }
        }
        return answer
    }

    data class Process(val index: Int, val priority: Int)
}
