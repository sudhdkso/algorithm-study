import java.util.*;

class Solution {
    
    public class Homework implements Comparable<Homework>{
        String name;
        int start;
        int playtime;
        
        public Homework(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        
        public Homework(String name, String start, String playtime){
            this(name, convertTimeToInt(start), Integer.parseInt(playtime));
        }
           
        @Override
        public int compareTo(Homework e){
            return Integer.compare(this.start, e.start);
        }
    }
    
    public String[] solution(String[][] plans) {
        //종료한 과제
        List<String> end = new ArrayList<>();
        //대기 중인 과제
        Stack<Homework> waitting = new Stack<>();
        //진행 중인 과제
        Stack<Homework> playing = new Stack<>();
        
        //이차원 배열 plans List<Homework>로 변환
        List<Homework> homeworks = setHomework(plans);
        
        //시작 시간을 기준으로 정렬
        Collections.sort(homeworks);
        
        
        for(Homework homework : homeworks){
            if(playing.isEmpty()){
                playing.push(homework);
                continue;
            }
            
            Homework now = playing.peek();
            int nowEnd = now.start+now.playtime;
            //진행 중인 과제가 다음 과제 시작 시각전에 끝나지 않은 경우
            if(nowEnd > homework.start){
                int remain = nowEnd - homework.start;
                //진행 중인 과제를 대기 과제에 넣고
                waitting.push(new Homework(now.name, now.start, remain));
            }
            //다음 과제 시작 시각 전에 진행 중인 과제가 종료되었을 때
            else {
                //종료 과제 리스트에 넣고
                end.add(now.name);
                
                //다음 과제 시작 시각 전에 남은 시간 
                int remain = homework.start - nowEnd;
                
                //대기 과제가 있을 때
                while(!waitting.isEmpty()){
                    if(remain <= 0){
                        break;
                    }
                    Homework wait = waitting.pop();
                    
                    //대기 과제의 과제 진행 시간이 남은 시간보다 작을 때
                    if(remain >= wait.playtime){
                        end.add(wait.name);
                    }
                    else {
                        waitting.push(new Homework(wait.name, wait.start, wait.playtime-remain));
                    }
                    remain -= wait.playtime;
                }
            }
            //그 다음 과제 실행
            playing.push(homework);
        }
        
        end.add(playing.peek().name);
        //대기 열에 남은 과제들 최근 멈춘 순서대로 추가
        while(!waitting.isEmpty()){
            end.add(waitting.pop().name);
        }

        return end.stream().toArray(String[]::new);
    }
    
    private int convertTimeToInt(String time){
        return Integer.parseInt(time.split(":")[0])*60 + Integer.parseInt(time.split(":")[1]);
    }
    
    private List<Homework> setHomework(String[][] plans){
        List<Homework> list = new ArrayList<>();
        
        for(String[] plan : plans){
            list.add(new Homework(plan[0],plan[1],plan[2]));
        }
        return list;
    }
    
}