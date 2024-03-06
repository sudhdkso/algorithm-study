import java.util.*;

class Solution {
    private class Music implements Comparable<Music>{
        int index, play;
        public Music(int index, int play){
            this.index = index;
            this.play = play;
        }
        
        @Override
        public int compareTo(Music e){
            if(Integer.compare(e.play, this.play) == 0){
                return Integer.compare(this.index, e.index);
            }
            return Integer.compare(e.play, this.play);
        }
        
    }
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> totalPlay = new HashMap<>();
        Map<String, PriorityQueue<Music>> genresMap = new HashMap<>();
        
        int length = genres.length;
        
        for(int i=0;i<length;i++){
            String genre = genres[i];
            totalPlay.put(genre, totalPlay.getOrDefault(genre,0)+plays[i]);
            
            if(!genresMap.containsKey(genre)){
                genresMap.put(genre, new PriorityQueue<Music>());
            }
            genresMap.get(genre).offer(new Music(i, plays[i]));
        }
        
        List<String> sortedKey = sortGenresByPlayCount(totalPlay);
        
        return getBestAlbam(sortedKey, genresMap);
    }
    
    private List<String> sortGenresByPlayCount(Map<String, Integer> totalPlay){
        List<String> key = new ArrayList<>(totalPlay.keySet());
        
        key.sort((o1,o2) -> {
            return totalPlay.get(o2).compareTo(totalPlay.get(o1));
        });
        return key;
    }
    
    private int[] getBestAlbam(List<String> sortedKey, Map<String, PriorityQueue<Music>> genreMap){
        List<Integer> bestAlbam = new ArrayList<>();
        
        for(String key : sortedKey){
            PriorityQueue<Music> pq = genreMap.get(key);
            
            for(int i=0;i<2;i++){
                if(pq.isEmpty()){
                   break; 
                }
                bestAlbam.add(pq.poll().index);   
            }
        }
        return bestAlbam.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}