import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class Solution {
    private List<Integer[]> salesList = new ArrayList<>();
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        //answer[0] : emoticons plus buyer
        //answer[1] : emoticons total amount
        
        getAllSales(0, new int[emoticons.length]);
        
        for(Integer[] sales : salesList){
           int[] result = calcTotalEmoticons(users, emoticons, sales);
            
           if(result[0] > answer[0]){
             answer = result;
           } 
           else if (result[0] == answer[0] && result[1] > answer[1]){
             answer = result;
          }
        }
        return answer;
    }
    
    
    public void getAllSales(int index, int[] sales){
      if(index >= sales.length){
           salesList.add(Arrays.stream(sales).boxed().toArray(Integer[]::new));
           return;
      }
        
      for(int i=10;i<=40;i+=10){
          sales[index] = i;
          getAllSales(index+1, sales);
      }
    }
    
    public int[] calcTotalEmoticons(int[][] users, int[] emoticons, Integer[] sales){
        int[] result = new int[2];
        int[] salesEmoticons = calcSaleEmoticons(emoticons, sales);
        
        for(int i=0;i<users.length;i++){
            int amount = 0;
            for(int j =0;j<emoticons.length;j++){
               if(sales[j] >= users[i][0]){
                   amount += salesEmoticons[j];
               }
            }
            //
            if(amount >= users[i][1]){
                result[0]++;
            }
            else{
                result[1] += amount;
            }
        }
        
        return result;
    }
    
    public int[] calcSaleEmoticons(int[] emoticons, Integer[] sales){
        int[] salesEmoticons = new int[emoticons.length];
        
        for(int i = 0; i < emoticons.length; i++){
            salesEmoticons[i] = emoticons[i]* (100-sales[i])/100;
        }
        return salesEmoticons;
    }
    
    
}