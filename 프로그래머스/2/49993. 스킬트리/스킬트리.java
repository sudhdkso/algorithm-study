class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String skill_tree: skill_trees){
            String rs = removeAnotherSkill(skill, skill_tree);
            if(skill.indexOf(rs) == 0){
                answer++;
            }
        }
        
        return answer;
    }
    
    private static String removeAnotherSkill(String skill, String skill_tree){
        StringBuilder sb = new StringBuilder();
        
        for(char ch : skill_tree.toCharArray()){
            if(skill.indexOf(ch) >= 0){
                sb.append(ch);
            }
        }
        return sb.toString();
        
    }
    
  
}