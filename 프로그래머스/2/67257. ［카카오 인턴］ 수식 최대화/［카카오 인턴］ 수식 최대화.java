import java.util.*;
import java.util.stream.Collectors;
class Solution {
    private String[] priorities = {"+-*","+*-","-+*","-*+","*+-","*-+"};
    private List<Long> operand = new ArrayList<>();
    private List<Character> operator = new ArrayList<>();
    
    private long max = 0;
    
    public long solution(String expression) {
 
        parseExpression(expression);
        
        for(String priority : priorities) {
            calcExpression(priority.toCharArray(), 0, new ArrayList<>(operand), new ArrayList<>(operator));
            
        }
        
        return max;
    }
    
    private void parseExpression(String expression){
        
        operand = Arrays.stream(expression.split("[+*-]"))
            .map(Long::parseLong)
            .collect(Collectors.toList());
        
        for(char ex : expression.toCharArray()){
            if(ex == '+' || ex == '-' || ex == '*'){
                operator.add(ex);
            }
        }

    }
    
    private void calcExpression(char[] priority, int depth, List<Long> copyOperand, List<Character> copyOperator) {
        if(depth >= priority.length){
            max = Math.max(Math.abs(copyOperand.get(0)), max);
            return;
        }
        
        while(true){
            int index = copyOperator.indexOf(priority[depth]);

            if(index < 0){
                break;
            }
            
            long result = calculate(copyOperand.get(index), copyOperand.get(index+1), copyOperator.get(index));
            
            copyOperand.remove(index);
            copyOperand.remove(index);
            copyOperator.remove(index);
            
            copyOperand.add(index, result);
        }
        
        calcExpression(priority, depth+1, copyOperand, copyOperator);
        
    }
    private long calculate(long a, long b, char op){
        if(op == '+'){
            return a+b;
        }
        else if(op == '-'){
            return a-b;
        }
        else {
            return a*b;
        }
    }
}