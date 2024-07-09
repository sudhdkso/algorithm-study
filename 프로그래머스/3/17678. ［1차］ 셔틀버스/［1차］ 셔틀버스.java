import java.util.Arrays;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        Arrays.sort(timetable, (o1,o2) -> {
            int t1 = convertTimeToInt(o1);
            int t2 = convertTimeToInt(o2);
            return t1-t2;
        });
        int lastIndex = 0;
        int shuttle = convertTimeToInt("09:00");
        int last = -1, count = 0;
        while(n-- > 0){
            count = m;
            for(int i = lastIndex;i<timetable.length;i++){
                int waitting = convertTimeToInt(timetable[i]);
                if(count == 0){
                    break;
                }
                if(shuttle >= waitting){
                    count--;
                    last = waitting;
                    lastIndex = i+1;
                }
            }
            shuttle += t;
        }
       
        //자리가 남아있는데
        if(count > 0){
            //아무도 마지막 셔틀 전까지 줄을 안섰을 때 OR 마지막 줄선사람이 9시 보다 전일 때
            if(last == -1 || convertTimeToInt("09:00") >= last){
                answer = convertIntToTime(shuttle-t);
            }
            else answer = convertIntToTime(last);
        }
        else{//자리가 안 남아있으면
            //가장 마지막에 도착한 사람 1분전에 도착하기
            answer = convertIntToTime(last-1);
        }
        
        return answer;
    }
    
    public int convertTimeToInt(String time){
        return Integer.parseInt(time.split(":")[0])*60 + Integer.parseInt(time.split(":")[1]);
    }
    
    public String convertIntToTime(int time){
        String HH = String.valueOf(time/60);
        String MM = String.valueOf(time%60);
        HH = HH.length() == 1 ? "0"+HH : HH;
        MM = MM.length() == 1 ? "0"+MM : MM;
        return String.format("%s:%s",HH,MM);
    }
    
}