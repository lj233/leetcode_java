package QueueAndStack.test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * @author lijian
 * @description 克隆图  无向图 深拷贝
 * @date 2019/12/17
 */
public class Main133 {
}

class Solution133 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        queue.offer(node);
        //先生成第一个节点
        Node n = new Node();
        n.val = node.val;
        n.neighbors = new ArrayList<Node>();
        map.put(n.val, n);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node temp : cur.neighbors) {
                //没有生成的节点就生成
                if (!map.containsKey(temp.val)) {
                    n = new Node();
                    n.val = temp.val;
                    n.neighbors = new ArrayList<Node>();
                    map.put(n.val, n);
                    queue.offer(temp);
                }
                map.get(cur.val).neighbors.add(map.get(temp.val));
            }
        }

        return map.get(node.val);
    }


    public Node DFS(Node node) {
        if (node == null) {
            return node;
        }
        Map<Integer, Node> map = new HashMap<>();
        return cloneGrapthHelper(node, map);
    }

    private Node cloneGrapthHelper(Node node, Map<Integer, Node> map) {
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }
        //生成当前节点
        Node n = new Node();
        n.val = node.val;
        n.neighbors = new ArrayList<Node>();
        map.put(node.val, n);
        //添加它的所有邻居节点
        for (Node temp : node.neighbors) {
            n.neighbors.add(cloneGrapthHelper(temp, map));
        }
        return n;

    }

}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

