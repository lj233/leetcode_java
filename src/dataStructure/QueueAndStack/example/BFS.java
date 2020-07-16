package dataStructure.QueueAndStack.example;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author lijian
 * @description 广度优先搜索
 * 找出从根节点到目标节点的最短路径
 * @date 2019/10/21
 */
public class BFS {


    static int map[][] = new int[4][4];

    public static void main(String[] args) {
        //填充初始值
        Arrays.fill(map, 0);

        new BFS().bfs(3, 3);
    }

    public void bfs(int end_r, int end_c) {

        int next[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};// 4个方向
        LinkedList<Node> q = new LinkedList<Node>();// 队列存储
        Node start = new Node(0, 0, 0);
        q.offer(start);
        while (!q.isEmpty()) {

            Node temp = q.poll();
            if (temp.row == end_r && temp.cloumm == end_c) {
                System.out.println(temp.round);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int r = temp.row + next[i][0];
                int c = temp.cloumm + next[i][1];
                if (r > 3 || c > 3 || r < 0 || c < 0 || map[r][c] == 1) {
                    continue;
                }
                map[r][c] = 1;
                q.offer(new Node(r, c, temp.round + 1));
            }
        }
    }

    class Node {
        int row;
        int cloumm;
        int round;

        public Node(int row, int cloumm, int round) {
            super();
            this.row = row;
            this.cloumm = cloumm;
            this.round = round;
        }

    }

}
