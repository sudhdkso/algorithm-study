class Solution {
    fun solution(record: Array<String>): Array<String> {
        var answer = arrayOf<String>()
        
        val nicknameMap : HashMap<String, String> = hashMapOf()
        for(rec in record) {
            val recParts = rec.split(" ")
            if(recParts[0] == "Enter" || recParts[0] == "Change"){
                nicknameMap.put(recParts[1], recParts[2])
            }
        }
        
        val recordList : MutableList<String> = mutableListOf()
        
        for(rec in record) {
            val recParts = rec.split(" ")
            if(recParts[0] == "Enter"){
                recordList.add("${nicknameMap.get(recParts[1])}님이 들어왔습니다.")
            }
            else if(recParts[0] == "Leave"){
                recordList.add("${nicknameMap.get(recParts[1])}님이 나갔습니다.")
            }
        }
        return recordList.map { it }.toTypedArray()
    }
}