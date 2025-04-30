class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int len = schedules.length;
        
        for(int i=0;i<timelogs.length;i++){
            int schedule = schedules[i];
            int day = startday, count = 0;
            for(int j=0;j<timelogs[i].length;j++){
                if((day < 6 && isEnter(schedule, timelogs[i][j])) || day >= 6){
                    count++;
                }
                day = day%7 + 1;
            }
            if(count == 7) answer++;
        }
        return answer;
    }
    
    public static boolean isEnter(int schedules, int workTime){
        int maxSchedules = fixTimeFormat(schedules + 10);
        return maxSchedules >= workTime;
    }
    
    public static int fixTimeFormat(int time){
        int hh = time/100;
        int mm = time%100;
        
        int total = hh*60 + mm;
        int fhh = total / 60;
        int fmm = total % 60;
    
        return fhh * 100 + fmm;
    }
}