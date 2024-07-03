class Solution {
    fun solution(numbers: IntArray): String {
        var answer = ""
        val sortedNumbers = numbers
            .sortedWith { a, b ->
                val strA = a.toString()
                val strB = b.toString()
                (strB + strA).compareTo(strA + strB)
            }
        if(sortedNumbers[0] == 0){
            return "0"
        }
        return sortedNumbers.joinToString("")
    }
}