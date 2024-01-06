import java.util.*;

class Solution {
    private class Counsel implements Comparable<Counsel>{
        int start;
        int end;
        
        public Counsel(int start, int end){
            this.start = start;
            this.end = end;
        }
        
        public boolean isEnd(int time){
            return time >= end;
        }
        
        @Override
        public int compareTo(Counsel c){
            return Integer.compare(this.end, c.end);
        }
    }
    
    public int solution(int k, int n, int[][] reqs) {
        
        int answer = 0;
        int remain = n - k;
                
        Map<Integer, List<Counsel>> map = setCounsel(reqs);
        
        //유형 별 상담원 수의 따른 대기 시간
        int[][] waitTimeOfType = getTotalWaitTimeOfType(n, k , map);
        
        //상담 유형별 할당되는 상담원의 수
        // i -> i유형의 상담원 수
        int[] counselors = new int[k+1];  
        Arrays.fill(counselors, 1);
        
        while(remain-- > 0){
            int maxReduce = 0;
            int index = 0;
            for(int type=1;type<=k;type++){
                int count = counselors[type];
                int before = waitTimeOfType[type][count];
                
                int after = waitTimeOfType[type][count+1];
                
                int reduce = before - after;
                if(maxReduce <= reduce){
                    maxReduce = reduce;
                    index = type;
                }
            }
            counselors[index]++;
        }
        
        return sumTotalWaitTime(counselors, waitTimeOfType);
    }
    
    //상담 유형별 상담 시간을 분류하는 함수
    private Map<Integer, List<Counsel>> setCounsel(int[][] reqs){
        Map<Integer, List<Counsel>> map = new HashMap<>();
        
        for(int[] req : reqs){
            int start = req[0];
            int time = req[1];
            int type = req[2];
            
            map.put(type, map.getOrDefault(type, new ArrayList<Counsel>()));
            map.get(type).add(new Counsel(start, start+time)); 
        }
        
        return map;
    }
    
    private int[][] getTotalWaitTimeOfType(int n, int k, Map<Integer, List<Counsel>> map){
        int maxCounselor = n-k+1;
        int[][] waittingTime = new int[n+1][maxCounselor+1];
        //각 유형별 상담원의 수에 따른 대기 시간
        //(i,j) -> i타입의 상담원이 j명있을 때 대기 시간
        for(int type=1;type<=k;type++){
            for(int count=1;count<=maxCounselor;count++){
                if(!map.containsKey(type)){
                    continue;
                }
                waittingTime[type][count] = calcWaitTime(map.get(type), count);
            }
        }
        return waittingTime;
    }
    
    //상담원의 수에 따른 상담 대기 시간을 구하는 함수
    private int calcWaitTime(List<Counsel> counseles, int count){
        int wait = 0;
        PriorityQueue<Counsel> pq = new PriorityQueue<>();

        for(Counsel counsel : counseles){
            int start = counsel.start;
            int end = counsel.end;
            //1. 해당 유형의 상담인원 중 상담 가능한 인원이 있으면 해당 상담원 배치
            if(pq.isEmpty() || pq.size() < count){
                pq.offer(new Counsel(start, end));
            }
            //2. 배치가능한 인원이 없는 경우 가장 먼저 끝나는 상담 시간과 현재 상담의 시작 시간을 비교
            else if(pq.peek().isEnd(start)){
                pq.poll();
                pq.offer(new Counsel(start, end));
            }
            //3. 해당 유형의 상담임원 중 상담 가능한 인원이 없으면 기다림
            else{
                //기다리면 앞의 상담 시간 종료 이후 바로 시작해야함
                //앞의 상담 종료 시간
                int bEnd = pq.poll().end;
                wait += bEnd - start;
                
                pq.offer(new Counsel(bEnd, bEnd+(end-start)));
            }
        }
        return wait;
    }
    
    private int sumTotalWaitTime(int[] counselors, int[][] waitTimeOfType){
        int sum = 0;
        for(int i=1;i<counselors.length;i++){
            sum += waitTimeOfType[i][counselors[i]];
        }
        return sum;
    }
}