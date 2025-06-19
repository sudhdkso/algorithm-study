import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private static class Music implements Comparable<Music>{
        int index, count;
        
        public Music(int index, int count){
            this.index = index;
            this.count = count;
        }
        
        @Override
        public int compareTo(Music obj){
            if(this.count == obj.count){
                return this.index-obj.index;
            }
            return obj.count-this.count;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> musicCountMap = new HashMap<>();
        Map<String,List<Music>> musicMap = new HashMap<>();
        
        //장르별 재생횟수 및 장르별 노래 분류
        for(int i=0;i<genres.length;i++){
            musicCountMap.put(genres[i], musicCountMap.getOrDefault(genres[i],0)+plays[i]);
            if(musicMap.getOrDefault(genres[i], null) == null){
                musicMap.put(genres[i], new ArrayList<Music>());
            }
            
            musicMap.get(genres[i]).add(new Music(i, plays[i]));
        }
        
        List<String> genresList = new ArrayList<>(musicCountMap.keySet());
        
        genresList.sort((o1,o2) -> musicCountMap.get(o2).compareTo(musicCountMap.get(o1)));
        
            
        List<Integer> answer = new ArrayList<>();
        
        int index = 0;
        
        for(String key: genresList){
            PriorityQueue<Music> pq = new PriorityQueue<>(musicMap.get(key));
            answer.add(pq.poll().index);
            if(pq.isEmpty()) continue;
            answer.add(pq.poll().index);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}