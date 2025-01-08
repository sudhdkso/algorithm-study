import java.util.Set;
import java.util.HashSet;

class Solution {
    private static Set<Integer> primeList = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        
        getNumberComb(new boolean[numbers.length()], new StringBuilder(), 0, numbers.toCharArray());
        return primeList.size();
    }
    
    private static void getNumberComb(boolean[] used, StringBuilder now,  int index, char[] arr){
        if(index >= arr.length){
            return;
        }
        int answer = 0;
        for(int i=0;i<arr.length;i++){
            if(used[i]) continue;
            used[i] = true;
            now.append(arr[i]);
            int num = Integer.parseInt(now.toString());
            if(isPrime(num)){
                primeList.add(num);
            }
            getNumberComb(used, now, index+1, arr);
            now.deleteCharAt(now.length() - 1);
            used[i] = false;

        }
    }
    
    
    //0 ~ 9999999
    private static boolean isPrime(int num){
        if(num <= 1) return false;
        for(int i=2;i*i<=num;i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }
}