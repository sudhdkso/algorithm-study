import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private static String[] priorities = {"+-*","+*-","-+*","-*+","*-+","*+-"};
    private static List<Long> result = new ArrayList<>();
    public long solution(String expression) {
        long answer = 0;
        List<Long> nums = Arrays.stream(expression.split("[-*+]"))
            .map(Long::parseLong)
            .collect(Collectors.toList());
        List<String> operators = Arrays.stream(expression.split("[0-9]{1,3}"))
            .filter(x -> !x.equals(""))
            .collect(Collectors.toList());
        
        for(String priority: priorities){
            dfs(new ArrayList<>(nums), new ArrayList<>(operators), priority.toCharArray(), 0);
        }
        Collections.sort(result, Collections.reverseOrder());
        return result.get(0);
    }
    

    private static void dfs(List<Long> nums, List<String> operators, char[] priority, int depth){
        if(depth >= priority.length){
            result.add(Math.abs(nums.get(0)));
            return;
        }
        char op = priority[depth];
        int index = operators.indexOf(String.valueOf(op));
        
        while(index >= 0){
            operators.remove(index);
            long num1 = nums.get(index);
            nums.remove(index);
            long num2 = nums.get(index);
            nums.remove(index);
            
            nums.add(index, calculate(op, num1, num2));
            index = operators.indexOf(String.valueOf(op));
        }
        dfs(nums, operators, priority, depth+1);
    }
    
    private static long calculate(char op, long num1, long num2){
        if(op == '+'){
            return num1 + num2;
        }
        else if(op == '-'){
            return num1 - num2;
        }
        else{
            return num1 * num2;
        }
    }
}