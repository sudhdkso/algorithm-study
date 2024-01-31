import java.util.Arrays;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        
        Arrays.sort(musicinfos, (o1,o2) -> {
            String[] s1 = o1.split(",");
            String[] s2 = o2.split(",");
            
            int start1 = convertTime(s1[0].split(":"));
            int start2 = convertTime(s2[0].split(":"));
            
            int end1 = convertTime(s1[1].split(":"));
            int end2 = convertTime(s2[1].split(":"));
            
            int playTime1 = end1 - start1;
            int playTime2 = end2 - start2;
            
            if(Integer.compare(playTime1, playTime2) == 0){
                return Integer.compare(start2, start1);
            }
            return Integer.compare(playTime1, playTime2);
        });
        
        for(String musicinfo : musicinfos){
            String[] music = musicinfo.split(",");
            if(checkMusic(m, music)){
                answer = music[2];
            }
        }
        return answer.equals("") ? "(None)" : answer;
    }
    
    private int convertTime(String[] time){
        return Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
    }
    
    private boolean checkMusic(String m, String[] musicinfo){
        int playTime = convertTime(musicinfo[1].split(":")) - convertTime(musicinfo[0].split(":"));
        int len = convertMusicSheet(m.split("")).length();
        
        
        String myMusicSheet = convertMusicSheet(m.split(""));
        String selectMusicSheet = convertMusicSheet(musicinfo[3].split(""));
        String modified = modifyMusicByPlayTime(selectMusicSheet, playTime);
        
        if(modified.contains(myMusicSheet)){
            return true;
        }
        return false;
        
    }
    
    private String convertMusicSheet(String[] music){
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<music.length;i++){
            if(music[i].equals("#")) continue;
            if(i+1 < music.length && music[i+1].equals("#")){
                sb.append(music[i].toLowerCase());
            }
            else{
                sb.append(music[i]);
            }
        }  
        return sb.toString();
    }
    
    private String modifyMusicByPlayTime(String music, int playTime){
        
        if(music.length() >= playTime){
            return music.substring(0, playTime);
        }
        StringBuilder sb = new StringBuilder(music);
        while(sb.length() < playTime){
            sb.append(music);
        }
        
        return sb.toString().substring(0,playTime);
    }
}