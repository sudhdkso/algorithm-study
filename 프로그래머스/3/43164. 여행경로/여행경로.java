import java.util.*;

class Solution {
    Map<String, List<String>> flight = new HashMap<>();
    boolean[] visited;
    List<String> allRoute = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        int count = tickets.length;
        visited = new boolean[count];
        
        for(int i=0;i<count;i++){
            String arr = tickets[i][0];
            String dst = tickets[i][1];
            if(!flight.containsKey(arr)){
                flight.put(arr, new ArrayList<>());
            }
            if(!flight.containsKey(dst)){
                flight.put(dst, new ArrayList<>());
            }
            flight.get(arr).add(dst);
        }
        
        for(String key : flight.keySet()){
            Collections.sort(flight.get(key));
        }
        Stack<String> route = new Stack<>();
        route.push("ICN");
        dfs("ICN", route, tickets, count+1);
        
        Collections.sort(allRoute);
        return allRoute.get(0).split(" ");
    }
    
    private int getTicketNumber(String arr, String dst, String[][] tickets){
        for(int i=0;i<tickets.length;i++){
            if(tickets[i][0].equals(arr) && tickets[i][1].equals(dst) && !visited[i]){
                return i;
            }
        }    
        return -1;
    }
    
    private void dfs(String current, Stack<String> route, String[][] tickets, int count){
        
        if(route.size() == count){
            String strRoute = convertRouteToString(route);
            if(!allRoute.contains(strRoute)){
                allRoute.add(strRoute);
            }
            return;
        }
        
        for(String next : flight.get(current)){
            int index = getTicketNumber(current, next, tickets);
            if(index < 0) continue;
            visited[index] = true;
            route.push(next);
            
            dfs(next, route, tickets, count);
            
            route.pop();
            visited[index] = false;
        }
        return;
    }
    
    private String convertRouteToString(Stack<String> route){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<route.size();i++){
            sb.append(route.get(i)).append(" ");
        }
        return sb.toString();
    }
}