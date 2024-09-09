import java.util.*;

class Solution {
    //할인율 1 ~ 40
    
    private List<int[]> sales = new ArrayList<>();
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        getSalesList(emoticons.length, 0, new int[emoticons.length]);
        
        for(int[] sale: sales){
            int[] arr = new int[2];
            for(int i=0;i<users.length;i++){
                int[] result = buyEmoticon(users[i], emoticons, sale);
                arr[0] += result[0];
                arr[1] += result[1];
            }
            if(arr[0] > answer[0] || (answer[0] == arr[0] && arr[1] > answer[1])){
                answer = Arrays.copyOf(arr,2);
            }
        }
        return answer;
    }
    
    private int[] buyEmoticon(int[] user, int[] emoticons, int[] sale){
        int result = 0;
        
        for(int i=0;i<emoticons.length;i++){
            if(sale[i] < user[0]) continue;
            int price = (int)((double)emoticons[i] * (double)(100-sale[i])/100.0);
            result += price;
        }
        if(result >= user[1]){
            return new int[]{1,0};
        }
        return new int[]{0,result};
        
    
    }
    
    private void getSalesList(int len, int depth, int[] sale){
        if(depth >= len){
            sales.add(Arrays.copyOf(sale, len));
            return;
        }
        
        for(int i=10;i<=40;i+=10){
            sale[depth] = i;
            getSalesList(len,depth+1,sale);
        }
    }
}