import java.util.*

class Solution {
    
    fun solution(book_time: Array<Array<String>>): Int {
        var answer: Int = 0
        val pq = PriorityQueue<Reservation>(compareBy<Reservation> { it.transStart }.thenBy { it.transEnd })
        pq.addAll(book_time.map { Reservation(it[0], it[1]) }.toList())

        val room: MutableList<Int> = mutableListOf()

        while (pq.isNotEmpty()) {
            val now = pq.poll()
            if (room.size == 0) {
                room.add(now.transEnd)
                continue
            }
            var flag : Boolean = false
            for (index in room.indices) {
                if (now.transStart >= room[index] + 10) {
                    room.removeAt(index)
                    room.add(now.transEnd)
                    flag = true
                    break
                }
            }
            
            if(!flag) {
                room.add(now.transEnd)
            }
        }
        
        return room.size
    }

    data class Reservation(val start: String, val end: String){
        val transStart: Int = convertTimeType(start.split(":"))
        val transEnd: Int = convertTimeType(end.split(":"))
        private fun convertTimeType(time: List<String>) : Int {
            return ((time[0].toInt()) * 60) + (time[1].toInt())
        }
    }
}