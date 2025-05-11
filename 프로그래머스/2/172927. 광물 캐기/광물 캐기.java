import java.util.*;

class Solution {
    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int pickNum = 0;

        for(int i=0;i<picks.length;i++){
            pickNum+=picks[i];
        }
        int[][] fatigues = calcFatigue(minerals, pickNum);
        int length = fatigues.length;
        Arrays.sort(fatigues, (o1,o2) -> {
            if(o1[2] == o2[2]){
                if(o1[1] == o2[1]){
                    return o2[0] - o1[0];
                }
                return o2[1]-o1[1];
            }
            return o2[2]-o1[2];
        });

        for(int i=0;i<length;i++){
            if(picks[0] > 0){
                answer+= fatigues[i][0];
                picks[0]--;
            }
            else if(picks[1] > 0){
                answer+= fatigues[i][1];
                picks[1]--;
            }
            else if(picks[2] > 0){
                answer+= fatigues[i][2];
                picks[2]--;
            }
            else {
                break;
            }
        }


        return answer;
    }

    public static int[][] calcFatigue(String[] minerals, int pickNum){
        int length = minerals.length > pickNum*5 ? pickNum*5 : minerals.length;
        int size = (length/5)+ (length%5 > 0 ? 1 : 0);
        int[][] fatigues = new int[size][3];

        for(int i=0;i<length;i++){
            if(minerals[i].equals("diamond")){
                fatigues[i/5][0] += 1;
                fatigues[i/5][1] += 5;
                fatigues[i/5][2] += 25;
            }
            else if(minerals[i].equals("iron")){
                fatigues[i/5][0] += 1;
                fatigues[i/5][1] += 1;
                fatigues[i/5][2] += 5;
            }
            else if(minerals[i].equals("stone")){
                fatigues[i/5][0] += 1;
                fatigues[i/5][1] += 1;
                fatigues[i/5][2] += 1;
            }
        }
        return fatigues;
    }
}