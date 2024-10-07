import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private class Robot{
        int r, c, index;
        public Robot(int r, int c, int index){
            this.r = r;
            this.c = c;
            this.index = index;
        }

        public void moveToTarget(int R, int C){
            if(this.r > R){
                this.r--;
            }
            else if(this.r < R){
                this.r++;
            }
            else if(this.c > C){
                this.c--;
            }
            else if(this.c < C){
                this.c++;
            }

        }

        public boolean isArrived(int R, int C){
            return this.r == R && this.c == C;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        Queue<Robot> robots = new LinkedList<>();

        List<int[]>[] robot_routes = new List[routes.length];

        for(int i=0;i<routes.length;i++){
            robot_routes[i] = new ArrayList<>();

            int s = routes[i][0] - 1;
            int r = points[s][0];
            int c = points[s][1];
            robots.offer(new Robot(r,c,i));
        }


        while(!robots.isEmpty()){
            Robot robot = robots.poll();

            int i = 0;
            //robot_routes[robot.index].add(new int[]{robot.r, robot.c});
            for(i=0;i<routes[robot.index].length;){
                int s = routes[robot.index][i] - 1;
                int target[] = points[s];

                robot.moveToTarget(target[0], target[1]);
                robot_routes[robot.index].add(new int[]{robot.r, robot.c});

                if(robot.isArrived(target[0], target[1])){
                    i++;
                }

            }
        }

        int max = 0;
        for(int i=0;i< robot_routes.length;i++){
            max = Math.max(robot_routes[i].size(), max);
        }


        for(int i=0;i<max;i++){
            List<List<Integer>> position = new ArrayList<>();
            Set<List<Integer>> collisionPoint = new HashSet<>();

            for(int j=0;j<robot_routes.length;j++){
                if(robot_routes[j].size() <= i) continue;
                List<Integer> list = Arrays.stream(robot_routes[j].get(i))
                    .boxed()
                    .collect(Collectors.toList());
                if(!position.contains(list)){
                    position.add(list);
                }
                else{
                    collisionPoint.add(list);
                }
            }

            answer += collisionPoint.size();
        }
        return answer;
    }
}