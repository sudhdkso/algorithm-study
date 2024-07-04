class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        val parkMap : HashMap<String, Int> = hashMapOf()
        val inRecords = records.filter { it.split(" ")[2] == "IN" }
        val outRecords = records.filter {it.split(" ")[2] == "OUT" }
        val inCheck : BooleanArray = BooleanArray(inRecords.size)
        val outCheck : BooleanArray = BooleanArray(outRecords.size)

        for (i in inRecords.indices) {
            val inRecordParts = inRecords[i].split(" ")
            var inTime = convertTimeToInt(inRecordParts[0])
            val inNumber = inRecordParts[1]

            for (j in outRecords.indices) {
                val outRecordParts = outRecords[j].split(" ")
                val outTime = convertTimeToInt(outRecordParts[0])
                val outNumber = outRecordParts[1]
                if(inNumber == outNumber && !outCheck[j]){
                    inCheck[i] = true
                    outCheck[j] = true
                    parkMap.put(inNumber, parkMap.getOrDefault(inNumber, 0 ) + (outTime - inTime))
                    break
                }
            }
            if(!inCheck[i]){
                parkMap.put(inNumber, parkMap.getOrDefault(inNumber, 0 ) + ( convertTimeToInt("23:59")- inTime))
            }
        }


        return parkMap.toList().sortedBy { it.first }
            .toMap()
            .map { it -> calcFees(it.value, fees) }
            .toIntArray()
    }

    private fun convertTimeToInt(time: String): Int {
        return time.split(":")[0].toInt() * 60 + time.split(":")[1].toInt()
    }

    private fun calcFees(time: Int, fees: IntArray) : Int {
        val baseTime = fees[0]
        val baseFee = fees[1]
        val unitTime = fees[2]
        val unitFee = fees[3]

        if(time <= baseTime) {
            return baseFee
        }
        return (Math.ceil((time - baseTime).toDouble() / unitTime.toDouble()) * unitFee).toInt() + baseFee
    }
}