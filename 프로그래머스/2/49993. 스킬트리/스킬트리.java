class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String skill_tree : skill_trees){
            String s = remove(skill_tree, skill);
            if(skill.indexOf(s) == 0){
                answer++;
            }
        }
        return answer;
    }

    
    private static String remove(String skill_tree, String skill){
        StringBuilder sb = new StringBuilder();
        for(char ch : skill_tree.toCharArray()){
            if(skill.indexOf(ch) >= 0){
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}