
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int stationsCount = stations.length;
        int range = (2*w)+1;
        
        if(stations[0]-w-1 > 0){
            answer += calcAdditinalStations(1, stations[0]-w-1, range);
        }
        
        for(int i=1;i<stationsCount;i++){
            answer += calcAdditinalStations(stations[i-1]+w+1,stations[i]-w-1, range);
        }
        
        if(stations[stationsCount-1]+w+1 <= n){
            answer += calcAdditinalStations(stations[stationsCount-1]+w+1,n, range);
        }
        
        return answer;
    }
    
    private int calcLength(int left, int right){
        return (right-left)+1;
    }
    
    private int calcAdditinalStations(int left, int right, int range){
        int length = calcLength(left,right);

        return ceil((double)length/(double)(range));
    }
    
    private int ceil(double d){
        int r = (int)d;
        if(d < 0 || (double)r == d){
            return r;
        }
        return r+1;
    }
}