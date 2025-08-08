class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i=0;i<numbers.length;i++){
            if(numbers[i]%2 == 0)   answer[i] = numbers[i]+1;
            else answer[i] = findByNextNumber(numbers[i]);
        }
        return answer;
    }
    
    private static Long findByNextNumber(long number){
        StringBuilder binary = new StringBuilder(Long.toBinaryString(number));
        int index = binary.lastIndexOf("01");
        if(index >= 0){
            binary.delete(index, index+2);
            binary.insert(index, "10");            
        }
        else{
            binary.delete(0,1);
            binary.insert(0, "10");
        }
        return Long.parseLong(binary.toString(),2);
    }
}